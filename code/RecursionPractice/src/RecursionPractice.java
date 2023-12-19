import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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
public final class RecursionPractice {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private RecursionPractice() {
    }

    /**
     * print from 1 to n*.
     *
     * @param n
     *            n
     * @param stream
     *            stream
     */
    private static void oneToN(int n, SimpleWriter stream) {
        stream.print(n + " ");
        if (n > 1) {
            oneToN(n - 1, stream);
        }
    }

    /**
     * print nth fibonacci number.
     *
     * @param n
     *            n
     * @return nth fibonacci
     */
    private static int fib(int n) {
        // (n-2) + (n-1)
        int nthFib = 0;
        if (n > 1) {
            nthFib = fib(n - 2) + fib(n - 1);
        } else {
            nthFib = n;
        }
        return nthFib;
    }

    /**
     * print nth factorial number.
     *
     * @param n
     *            n
     * @return nth factorial
     */
    private static int fact(int n) {

        int nthFact = 1;

        if (n > 1) {
            nthFact = n * fact(n - 1);
        }
        return nthFact;
    }

    /**
     * count number of digits in a natural number.
     *
     * @param num
     *            n
     * @return numer of digits
     */
    private static int countNNDigits(NaturalNumber num) {
        // play with returns for int counters , also use if/else structure
        // also just read directions lol u had it there

        NaturalNumber ten = new NaturalNumber1L("10");

        if (num.compareTo(ten) <= 0) {
            return 1;
        } else {
            num.divideBy10();
            return 1 + countNNDigits(num);
        }

    }

    /**
     * recursively reverse natural number
     *
     * @param n
     *            natural number to reverse
     */
    private static void revNaturalNumber(NaturalNumber n) {
// smaller problem: n.copyFrom(nRev.divide(10)

        NaturalNumber nRev = new NaturalNumber1L("0");

        NaturalNumber ten = new NaturalNumber1L("10");
        while (n.divideBy10() >= 0) {
            // add the last digit

            nRev.multiplyBy10(n.divideBy10());

        }

        n.copyFrom(nRev);
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
         * Put your main program code here; it may call myMethod as shown
         */
//oneToN(15, out);
//out.println();
//out.println(fib(6));
//out.println(fact(5));
        NaturalNumber test = new NaturalNumber1L("159");
        out.print(countNNDigits(test));
        out.print(test);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
