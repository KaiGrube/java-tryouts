package secrets;

import org.junit.BeforeClass;
import org.junit.Test;
import software.amazon.awssdk.regions.Region;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SecretCheckerTest {

    private static SecretChecker secretChecker;
    private static Config config;

    // values not matching/invalid
    private static final Region REGION_NOT_CONTAINING_SECRET = Region.AF_SOUTH_1;
    private static final String SECRET_KEY_NOT_MATCHING = "unknown_API_KEY";
    private static final String SECRET_VALUE_NOT_MATCHING = "invalid_key";


    @BeforeClass
    public static void setup() {
        config = new Config(
                Region.US_EAST_1,
                "myAwsSecretName",
                "myAwsSecretKey",
                "myAwsSecretValue"
        );
        secretChecker = new SecretChecker();
    }

    // todo: write test for bad connection
    @Test
    public void testRequestSecret() {
        assertTrue(secretChecker.requestSecret(config.REGION, config.SECRET_NAME).isPresent());
        assertFalse(secretChecker.requestSecret(REGION_NOT_CONTAINING_SECRET, config.SECRET_NAME).isPresent());
        assertFalse(secretChecker.requestSecret(config.REGION, SECRET_KEY_NOT_MATCHING).isPresent());
    }

    @Test
    public void testIsSecretValueMatching() {
        assertTrue("isSecretValueMatching should return true if valid secret value was passed.",
               secretChecker.isSecretValueMatching(config.REGION, config.SECRET_NAME, config.SECRET_KEY, config.SECRET_VALUE));
        assertFalse("isSecretValueMatching should return false if invalid secret value was passed.",
                secretChecker.isSecretValueMatching(config.REGION, config.SECRET_NAME, config.SECRET_KEY, SECRET_VALUE_NOT_MATCHING));
    }

}
