import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaFilter {
    public static void main(String[] args) {
        Persons persons = new Persons("Anton", "Berta");
        persons.printNames(s -> s.startsWith("A"));
        persons.printNames(s -> s.startsWith("B"));
    }
}

class Persons {
    private final List<String> names;

    Persons(final String... names) {
        this.names = Arrays.asList(names);
    }

    void printNames(Predicate<String> filter) {
        for (String name : names) {
            if(filter.test(name)) System.out.println(name);
        }
    }
}


//interface MyFilter {
//    boolean test(String name);
//}

