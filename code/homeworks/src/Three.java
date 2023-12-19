import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * snippet from homework 3 in 2221 8/30/23.
 *
 * @author Mohammed maalin
 *
 */

public final class Three {
    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Three() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        int i = 0;
        double pi = 0.0;
        double lastPi = 0.0;

        double error = 1.0;

        final double epsilon = 0.0001;

        // while error is not less than epsilon
        // right now error is 1.0 to start loop
        // iterate pi
        // calculate new error
        // store two variables for difference and count iterations

        while (error > epsilon) {
            pi = lastPi + (2.0 * 2.0 * Math.pow(-1, i)) / ((2 * i) + 1);
            error = Math.abs(pi - lastPi);
            lastPi = pi;
            i++;
        }

        out.println("error is " + error + " and estimated pi is " + pi
                + " and number of iterations " + i);

        in.close();
        out.close();

    }
}
