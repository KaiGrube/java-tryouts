import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalTryouts {

    public static void printOptional(Optional<String> string) {
        try {
            System.out.println(string.get());
        } catch (NoSuchElementException e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        Optional<String> emptyOptional = Optional.empty();
        printOptional(emptyOptional);

        Optional<String> stringOptional = Optional.of("string1");
        printOptional(stringOptional);

//        Optional<String> nullOptional = Optional.of(null); // throws NullPointerException at ...requireNonNull
//        printOptional(nullOptional);

        Optional<String> nullOptional = Optional.ofNullable(null);
        printOptional(nullOptional); // throws java.util.NoSuchElementException: No value present
        System.out.println(nullOptional); // prints 'Optional.empty'

        Optional<String> opt = Optional.ofNullable("Hello :-)");
        if (opt.isPresent()) System.out.println(opt.get());
        opt.ifPresent(str -> System.out.println(str));
        opt.ifPresent(System.out::println);

    }
}
