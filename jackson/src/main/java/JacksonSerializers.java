import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import software.amazon.awssdk.regions.Region;

import java.io.File;
import java.io.IOException;

public class JacksonSerializers {

    private static void jacksonStandardSerializerWrite(Account account) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(account));
        String file = System.getProperty("user.dir") + "/account.json";
        objectMapper.writeValue(new File(file), account);
    }

    private static void jacksonCustomSerializerWrite(Account account) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(Account.class, new CustomAccountSerializer());
        objectMapper.registerModule(module);
        String carJson = objectMapper.writeValueAsString(account);
        System.out.println(carJson);
    }

    public static void main(String[] args) throws IOException {
        Account account = new Account(Region.AF_SOUTH_1);
        jacksonStandardSerializerWrite(account);
        jacksonCustomSerializerWrite(account);
    }

}

class CustomAccountSerializer extends StdSerializer<Account> {
    public CustomAccountSerializer() { this(null); }
    public CustomAccountSerializer(Class<Account> t) { super(t); }
    @Override
    public void serialize(Account account, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("region", account.getRegion().toString());
        jsonGenerator.writeEndObject();
    }
}
class Account {
    @JsonProperty("Region1")
    Region region;
    public Account(Region region) { this.region = region; }
    public Region getRegion() { return region; }
    public void setRegion(Region region) { this.region = region; }
}
