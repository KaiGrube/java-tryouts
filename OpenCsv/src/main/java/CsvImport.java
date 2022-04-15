import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

public class CsvImport {

    public static List<Zipcode> importZipCode() {
        URL url = CsvImport.class.getClassLoader().getResource("zipcodes_germany_part.csv");
        File file = new File(url.getFile());
        List<Zipcode> records = null;
        try {
            records = new CsvToBeanBuilder<Zipcode>(new FileReader(file))
                    .withType(Zipcode.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void main(String[] args)  {
        List<Zipcode> records = importZipCode();
        records.forEach(System.out::println);
    }
}
