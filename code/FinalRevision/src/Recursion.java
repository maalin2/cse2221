import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Recursion {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Recursion() {
    }

    /**
     * Problem 1.
     *
     * @param n
     *            number to print to
     */
    private static void print1ToN(int n) {
        SimpleWriter s = new SimpleWriter1L();
        s.print(n);
        if (n > 1) {
            s.print(", ");
            print1ToN(n - 1);

        }
        s.close();

    }

    /**
     * Problem 2.
     *
     * @param n
     *            which fibonacci number to get
     * @return nth fibonacci number
     */
    private static int fib(int n) {
        int nthFib = 0;
        if (n < 2) {
            nthFib = n; //use property of the sequence
        } else {
            nthFib = fib(n - 1) + fib(n - 2);
        }
        return nthFib;
    }

    /**
     * Problem 3.
     *
     * @param n
     *            which factorial to calculate
     * @return the factorial
     */
    private static double fact(int n) {
        double nthFact = 1;
        if (n > 1) {
            nthFact = n * fact(n - 1);
        }

        return nthFact;
    }

    /**
     * problem 4.
     *
     * @param num
     *            which number to check
     * @return digits
     */
    private static int countDigits(NaturalNumber num) {

        int count = 0;
        if (!num.isZero()) {
            int digit = num.divideBy10();

            count = 1 + countDigits(num);
            num.multiplyBy10(digit);
        }

        return count;

    }

    /**
     * Problem 5.
     *
     * @param num
     *            what to reverse
     * @return reversed
     */
    private static NaturalNumber revNN(NaturalNumber num) {
        NaturalNumber numRev = new NaturalNumber2(0);

        //Solve

        return numRev;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here
         */
        final int n = 30;
        print1ToN(n);
        out.print("\nthe " + n + "th fibonacci number is " + fib(n));
        out.print("\t832040");

        out.print("\nthe " + n + "th factorial is ");
        out.print(fact(n), 2, true);
        out.print(" \t2.653e+32\n");

        final NaturalNumber1L num = new NaturalNumber1L(5434);
        out.print(num + " has " + countDigits(num) + " digits");
        out.print("\t4\n");
        out.println(num + " reversed is " + revNN(num) + " and num is still "
                + num + "\t\t4345");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
