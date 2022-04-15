public class Foo {
    public String var1;
    public String var2;
    public Nested nested;

    public class Nested {
        public String var3;
        public String var4;

        @Override
        public String toString() {
            return "Nested{" +
                    "var3='" + var3 + '\'' +
                    ", var4='" + var4 + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Foo{" +
                "var1='" + var1 + '\'' +
                ", var2='" + var2 + '\'' +
                ", nested=" + nested +
                '}';
    }
}
