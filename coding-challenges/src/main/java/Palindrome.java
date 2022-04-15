public class Palindrome {

    static boolean isPalindrome(int x) {
        String string = String.valueOf(x);
        String reverseString = new StringBuilder(string).reverse().toString();
        return string.equals(reverseString);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No argument given.");
            System.exit(0);
        }

        if (isPalindrome(Integer.parseInt(args[0]))) {
            System.out.printf("%s is a palindrome", args[0]);
            System.exit(0);
        }

        System.out.printf("%s is not a palindrome", args[0]);
    }
}
