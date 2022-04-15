import io.github.cdimascio.dotenv.Dotenv;

public class DotEnv {

    private static final Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {
        System.out.println(dotenv.get("ENV_VAR1"));
        System.out.println(dotenv.get("ENV_VAR2"));
    }
}