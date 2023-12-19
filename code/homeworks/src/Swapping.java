import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Methods from homework 10.
 *
 * @author Mohammed Maalin
 *
 */
public final class Swapping {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Swapping() {
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber nTmp = new NaturalNumber1L(n2);
        n2.copyFrom(n1);
        n1.copyFrom(nTmp);
        nTmp.clear();
    }

    /**
     * Swaps the two given {@code NaturalNumber}s, using {@code transferFrom}.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNNTransferFrom(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber n2Tmp = new NaturalNumber1L(n2);
        NaturalNumber n1Tmp = new NaturalNumber1L(n1);
        n2.transferFrom(n1Tmp);
        n1.transferFrom(n2Tmp);
        n2Tmp.clear();
        n1Tmp.clear();
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        NaturalNumber nTmp = new NaturalNumber1L(n);
        n.multiply(nTmp);
        nTmp.clear();
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

        NaturalNumber x = new NaturalNumber1L(2);
        NaturalNumber y = new NaturalNumber1L(2 + 1);

        // problem 1
        out.println(x + " " + y);
        swapNN(x, y);
        out.println(x + " " + y);

        // problem 2
        swapNNTransferFrom(x, y);
        out.println(x + " " + y);

        // problem 3
        out.print(x + " squared is ");
        square(x);
        out.println(x);

        out.print(y + " squared is ");
        square(y);
        out.println(y);

        x.clear();
        y.clear();

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
