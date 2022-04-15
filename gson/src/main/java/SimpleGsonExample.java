import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SimpleGsonExample {
    private static void fromStringToObject() {
        String jsonString = "{\n" +
                "  \"var1\": \"var1\",\n" +
                "  \"var2\": \"var2\",\n" +
                "  \"nested\": {\n" +
                "    \"var3\" : \"var3\",\n" +
                "    \"var4\" : \"var4\"\n" +
                "  }\n" +
                "}";
        Foo foo =  new Gson().fromJson(jsonString, Foo.class);
        System.out.println(foo);
    }

    private static void fromFileToMap() throws URISyntaxException, IOException {
        URL url = SimpleGsonExample.class.getClassLoader().getResource("foo.json");
        Path path = Paths.get(url.toURI());
        Reader reader = Files.newBufferedReader(path);
        Map<?, ?> jsonMap = new Gson().fromJson(reader, Map.class);
        for (Map.Entry<?, ?> entry : jsonMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        fromFileToMap();
        fromStringToObject();
    }
}
