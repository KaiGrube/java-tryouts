import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestPalindrome {

    private static final Map<Integer, Boolean> testData = new HashMap<>() {{
       put(0, true);
       put(1, true);
       put(10, false);
       put(101, true);
       put(21012, true);
    }};

    @Test
    public void testIsPalindrome() {
        testData.forEach((key, value) -> {
            assertEquals(String.format("isPalindrome(%d) should return %b", key, value), Palindrome.isPalindrome(key), value);
        });
    }
}
