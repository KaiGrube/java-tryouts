public class PuffTheMagicXcrement {

    public static void main(String[] args) {

        // Fall 0 - O.'s test from yesterday
        int o = 1;
        int res = o++;
        System.out.println("o=" + o);
        System.out.println("res=" + res);

        // Fall 1
        int i = 1;
        i = i++;
        System.out.println("i=" + i);

        // Fall 2
        int j = 1;
        j = ++j;
        System.out.println("j=" + j);

        // Fall 3
        int k = 1;
        k = k--;
        System.out.println("k=" + k);

        // Fall 4
        int m = 1;
        m = --m;
        System.out.println("m=" + m);
    }
}
