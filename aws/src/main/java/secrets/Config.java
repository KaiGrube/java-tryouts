package secrets;

import software.amazon.awssdk.regions.Region;

public class Config {
    public Region REGION ;
    public String SECRET_NAME;
    public String SECRET_KEY;
    public String SECRET_VALUE;

    public Config(Region REGION, String SECRET_NAME, String SECRET_KEY, String SECRET_VALUE) {
        this.REGION = REGION;
        this.SECRET_NAME = SECRET_NAME;
        this.SECRET_KEY = SECRET_KEY;
        this.SECRET_VALUE = SECRET_VALUE;
    }
}
