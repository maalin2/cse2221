import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Computing square root using Newton's method to 0.01% error. Updated to avoid
 * division by zero.
 *
 * @author M. Maalin
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square of x to within relative error of 0.01%.
     * Unlike Newton1 this method makes a special effort to not divide by zero.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {

        // this is our desired maximum error, 0.01%^2
        final double epsilonSq = 0.000000001;

        // we use r to compute approximate sqrt
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

            /*
             * checks if r is zero and returns r to avoid division by zero. Also
             * prints error
             */
            final double doubleBound = 0.000000001;
            if (Math.abs(num - 0) < doubleBound) {
                out.println("Cannot divide by zero.");
                out.println("sqrt of 0 is 0");

            } else {
                out.println(sqrt(num));
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
