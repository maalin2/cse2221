import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework 3, estimates pi using monte carlo and boolean static method.
 *
 * @author M. Maalin
 *
 */
public final class StaticMethods {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private StaticMethods() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        // pi * r^2 so area must equal pi

        return ((Math.pow(xCoord, 2) + Math.pow(yCoord, 2)) < 1);

    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        int i = 1;
        int numberOfPoints = 0;
        Random rnd = new Random1L();
        double x = 0;
        double y = 0;

        int nCopy = n;

        while (i < nCopy) {
            // generate x and y
            x = 2.0 * rnd.nextDouble();
            y = 2.0 * rnd.nextDouble();

            // use pointisInCircle to add to numberOfPoints
            if (pointIsInCircle(x, y)) {
                numberOfPoints++;
            }

            i++;
        }
        return numberOfPoints;
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
         * main program code, using numberOfPointsInCircle and indirectly
         * pointIsInCircle
         */

        /*
         * ask for n use n to do monte carlo method gather estimate estimate =
         * area of square squared * numberofpts / n area squared to get ratio of
         * pts and multiply area again to get pi
         */

        out.println("How many iterations? ");
        double iterations = in.nextInteger();

        int ptsInCircle = numberOfPointsInCircle((int) iterations);

        int squareSquared = (int) Math.pow(2, (2 * 2));

        double estimate = squareSquared * (ptsInCircle / iterations);

        out.println("Estimate of area of circle: " + estimate);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
