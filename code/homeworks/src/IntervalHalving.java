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
public final class IntervalHalving {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private IntervalHalving() {
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int guess = n / 2;
        int high = (n + 1);
        int low = 0;

        while (Math.abs(high - low) > 1) {
            if (n < Math.pow(guess, r)) {
                high = guess;
            } else {
                low = guess;
            }
            guess = (high + low) / 2;
        }
        return low;
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

        out.println(root(500, 3));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}