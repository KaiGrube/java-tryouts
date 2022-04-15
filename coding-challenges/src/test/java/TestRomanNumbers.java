import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestRomanNumbers {

    private static final Map<String, Integer> stringsToTest = new HashMap<>() {{
       put("I", 1);
       put("II", 2);
       put("III", 3);
       put("IV", 4);
       put("V", 5);
       put("VI", 6);
       put("IX", 9);
       put("X", 10);
       put("XI", 11);
    }};

    @Test
    public void getValueOfRomanNumber() {
        stringsToTest.forEach((key, value) -> {
            assertEquals(String.format("getValueOfRomanNumber(%s) should return %d", key, value),
                    RomanNumbers.getValueOfRomanNumber(key), value.intValue());
        });
    }
}
