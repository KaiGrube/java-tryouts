public class SimpleEnumeration {
    enum ANIMALS { DOG, CAT };

    public static void main(String[] args) {
        String test = "DOG";
        for (ANIMALS animal : ANIMALS.values()) {
            if (test.equals(animal.toString())) {
                System.out.println(test);
            }
        }
    }
}