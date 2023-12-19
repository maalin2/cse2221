import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Computing square root using Newton's method to error desired by user, without
 * division by zero.
 *
 * @author M. Maalin
 *
 */
public final class Newton3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square of x to within relative error designated by
     * user.
     *
     * @param x
     *            positive number to compute square root of
     * @param error
     *            user's desired error between current and last term
     * @return estimate of square root
     */
    private static double sqrt(double x, double error) {
        /*
         * This is the user's desired error squared. the equation says we have
         * to have epsilon^2 > real error
         */
        final double epsilonSq = Math.pow(error, 2);

        // we use r to compute approximate sqrt
        double r = x;
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
                out.print(
                        "What would you like the error of approximation to be? ");
                double userError = in.nextDouble();
                out.println(sqrt(num, userError));
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
