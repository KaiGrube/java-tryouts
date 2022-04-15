package secrets;

import com.google.gson.Gson;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import java.util.Map;
import java.util.Optional;

public class SecretChecker {
    /**
     * @param region the AWS region where the secret is located, e.g. Region.US_EAST_1
     * @param secretName name of the AWS secret
     * @param secretKey name of the key, e.g. "API_KEY"
     * @param secretValue value from client that wants to authenticate. Will be compared to secret value stored in AWS
     * @return true if secretValue is equal to secret value stored in AWS, false if values do not match
     * @see <a href="https://docs.aws.amazon.com/code-samples/latest/catalog/javav2-secretsmanager-src-main-java-com-example-secrets-GetSecretValue.java.html">Example</a>
     */
    public boolean isSecretValueMatching(Region region, String secretName, String secretKey, String secretValue) {
        Optional<String> secret = requestSecret(region, secretName);
        if (secret.isEmpty()) return false;

        Gson gson = new Gson();
        try {
            Map jsonMap = gson.fromJson(secret.get(), Map.class);  // gson.fromJson may throw com.google.gson.JsonSyntaxException
            Optional<String> receivedSecretValue = Optional.ofNullable(jsonMap.get(secretKey).toString());
            if (receivedSecretValue.isEmpty()) return false;
            // System.out.println(receivedSecretValue);
            return receivedSecretValue.get().equals(secretValue);
        } catch (RuntimeException e) {
            System.err.println(e.getLocalizedMessage());  // todo: use logging instead
            return false;
        }
    }

    /**
     * <p>Request secret from AWS. To run this code example, ensure that you have setup your development environment, including your AWS credentials.
     * For information, see <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html">Get started</a>
     * @param region the AWS region where the secret is located, g.e. Region.US_EAST_1
     * @param secretName name of the AWS secret, e.g. "pressReleasesApiKey"
     * @return Secret as stringified json, e.g.: {"API_KEY":"secretValue123"}
     * @see <a href="https://docs.aws.amazon.com/code-samples/latest/catalog/javav2-secretsmanager-src-main-java-com-example-secrets-GetSecretValue.java.html">Example</a>
     */
    Optional<String> requestSecret(Region region, String secretName) {
        try (SecretsManagerClient secretsClient = SecretsManagerClient.builder().region(region).build();) {
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder().secretId(secretName).build();
            GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
            return Optional.ofNullable(valueResponse.secretString());
        } catch (SecretsManagerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());  // todo: use logging instead
            return Optional.empty();
        }
    }
}