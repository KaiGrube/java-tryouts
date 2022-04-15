import java.util.Map;

import static java.util.Map.entry;

public class RomanNumbers {

    private static final Map<String, Integer> symbols = Map.ofEntries(
            entry("I", 1),
            entry("V", 5),
            entry("X", 10),
            entry("L", 50),
            entry("C", 100),
            entry("D", 500),
            entry("M", 1000)
    );

    private static final Map<String, Integer> subtractions = Map.ofEntries(
            entry("IV", 4),
            entry("IX", 9),
            entry("XL", 50),
            entry("XC", 90),
            entry("CD", 500),
            entry("CM", 900)
    );

    static int getValueOfRomanNumber(String s) {
        int sum = 0;
        for (int p = 0; p < s.length(); p++) {
            char c_current = s.charAt(p);
            if (p < s.length()-1) {
                char c_next = s.charAt(p + 1);
                String sibling = "" + c_current + c_next;
                if (subtractions.containsKey(sibling)) {
                    sum += subtractions.get(sibling);
                    p++;
                    continue;
                }
            }
            sum += symbols.get("" + c_current);
        }
        return sum;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No argument given.");
            System.exit(0);
        }
        int value = getValueOfRomanNumber(args[0]);
        System.out.println(value);
    }
}