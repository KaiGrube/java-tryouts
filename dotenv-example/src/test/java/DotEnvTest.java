import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DotEnvTest {

    private static Dotenv dotenv;

    @BeforeClass
    public static void setup() {
        dotenv = Dotenv.load();
    }

    @Test
    public void testDotEnv() {
        Assert.assertEquals(dotenv.get("ENV_VAR1"), "value1");
        Assert.assertNotEquals(dotenv.get("ENV_VAR2"), "wrongValue");
    }
}
