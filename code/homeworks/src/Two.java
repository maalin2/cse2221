import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * snippet from homework 2 in 2221 8/29/23.
 *
 * @author Mohammed maalin
 *
 */
public final class Two {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Two() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        output.print("Number of points: ");
        int n = input.nextInteger();

        int ptsInInterval = 0, ptsInSubinterval = 0;

        Random1L rnd = new Random1L();
        final double threshold = 0.5;

        while (ptsInInterval < n) {
            double x = rnd.nextDouble();
            ptsInInterval++;
            if (x < threshold) {
                ptsInSubinterval++;
            }
        }

        double estimate = (Math.pow(2 * 2 * 2 + 2, 2) * ptsInSubinterval)
                / ptsInInterval;
        output.println("Estimate of percentage: " + estimate + "%");

        input.close();
        output.close();
    }
}
