import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Computing square root using Newton's method to 0.01% error.
 *
 * @author M. Maalin
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square of x to within relative error of 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {

        // this is our desired maximum error, 0.01%^2
        final double epsilonSq = 0.000000001;

        // we use r (for root) to compute approximate sqrt
        double r = x;

        // abs(r^2 - x) / x is our current error
        double epsilonReal = (Math.abs(Math.pow(r, 2) - x)) / x;

        while (epsilonReal > epsilonSq) {
            r = (r + (x / r)) / 2;
            epsilonReal = (Math.abs(Math.pow(r, 2) - x)) / x;
        }

        return r;
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

        out.print("Would you like to compute a square root? ");
        String response = in.nextLine();

        if (response.equals("y")) {
            out.print("Please provide a positive number: ");
            double num = in.nextDouble();
            out.println(sqrt(num));
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
