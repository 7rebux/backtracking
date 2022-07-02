package de.nosswald;

/**
 * @author Nils Osswald
 */
public class Main {

    private static int calls = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.print(fibRec(i) + " ");

        System.out.println();

        for (int i = 0; i < 10; i++)
            System.out.print(fibIt(i) + " ");

        System.out.println();


        long start = System.currentTimeMillis();
        calls = 0;
        System.out.print(fibIt(42));
        System.out.println(": iterative took " + (System.currentTimeMillis()-start)
                + "ms and " + calls + " calls");
        start = System.currentTimeMillis();
        calls = 0;
        System.out.print(fibRec(42));
        System.out.println(": recursive took " + (System.currentTimeMillis()-start)
                + "ms and " + calls + " calls");
    }

    private static int fibRec(int n) {
        calls++;

        if (n <= 1)
            return n;

        return fibRec(n-1) + fibRec(n-2);
    }

    private static int fibIt(int n) {
        int result = 1;
        int previous = -1;

        for (int i = 0; i <= n; i++) {
            calls++;

            int temp = result + previous;
            previous = result;
            result = temp;
        }

        return result;
    }
}
