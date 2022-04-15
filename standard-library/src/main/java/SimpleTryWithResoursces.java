import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleTryWithResoursces {

    // bad example
    static String readFirstLine_bad(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        try {
            return br.readLine();
        } finally {
            br.close();
            fr.close(); // might not be closed !!!
        }
    }

    // better, using try with resources
    static String readFirstLine_better(String path) throws IOException {
        FileReader fr = new FileReader(path);
        try (fr; BufferedReader br = new BufferedReader(fr)) {
            return br.readLine();
        }
    }

}
