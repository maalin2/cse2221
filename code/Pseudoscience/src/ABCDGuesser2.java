import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Using the de Jager formula to approximate a given number using four given
 * constants to a reasonable degree of accuracy. Updated to estimate using a
 * static function.
 *
 * @author Mohammed Maalin
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        int i = 1;
        String input = "a";
        double positiveDouble = 0;

        while (i == 1) {
            out.print("Please provide a number: ");
            input = in.nextLine();

            if (FormatChecker.canParseDouble(input)) {
                positiveDouble = Double.parseDouble(input);

                if (positiveDouble > 0) {
                    i = 0;
                }
            }
        }

        return positiveDouble;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        double notOne = 1;
        int i = 1;
        String input = "a";

        final double doubleError = 0.000000000000001;
        while (i == 1) {
            out.print("Please enter a positive number not equal to one. ");
            input = in.nextLine();

            if (FormatChecker.canParseDouble(input)) {
                notOne = Double.parseDouble(input);

                if (Math.abs(notOne - 1) > doubleError) {
                    i = 0;
                }
            }

        }

        return notOne;
    }

    /**
     * Uses the four constants and assumed exponents in the de Jager formula to
     * guess a number to within 1% accuracy.
     *
     *
     * @param w
     *            the parameter w in the de Jager formula
     * @param x
     *            the parameter x in the de Jager formula
     * @param y
     *            the parameter y in the de Jager formula
     * @param z
     *            the parameter z in the de Jager formula
     * @param mu
     *            the positive number to estimate
     *
     * @return an array with the real terms in order being: the most accurate
     *         guess, its error, and the best exponents
     *
     * @requires {w,x,y,z} > 0 and {w,x,y,z} != 1 and mu > 0
     * @ensures <pre>
     *         w^a * x^b * y^c * z^d >= 0.99 * mu
     * </pre>
     *
     *
     */
    private static double[] deJager(double w, double x, double y, double z,
            double mu) {
        final double[] exponents = { -5, -4, -3, -2, -1, -(1.0 / 2.0),
                -(1.0 / 3.0), -(1.0 / 4.0), 0, (1.0 / 4.0), (1.0 / 3.0), 0.5, 1,
                2, 3, 4, 5 };

        double error = 1.0;
        double guess = 0;
        double bestGuess = 0;

        final double onePercent = 0.01;
        double bestError = onePercent * mu;

        double bestA = 1;
        double bestB = 1;
        double bestC = 1;
        double bestD = 1;

        final double percent = 100.0;

        // already have i
        for (int it = 0; it < exponents.length; it++) {
            for (int itTwo = 0; itTwo < exponents.length; itTwo++) {
                for (int itThree = 0; itThree < exponents.length; itThree++) {

                    for (int itFour = 0; itFour < exponents.length; itFour++) {
                        guess = Math.pow(w, exponents[it])
                                * Math.pow(x, exponents[itTwo])
                                * Math.pow(y, exponents[itThree])
                                * Math.pow(z, exponents[itFour]);

                        error = (percent * (Math.abs((guess - mu) / mu)));

                        if (error < bestError) {
                            bestError = error;
                            bestGuess = guess;

                            bestA = exponents[it];
                            bestB = exponents[itTwo];
                            bestC = exponents[itThree];
                            bestD = exponents[itFour];

                        }
                    }
                }
            }
        }

        double[] guessInformation = { bestGuess, bestError, bestA, bestB, bestC,
                bestD };

        return guessInformation;
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

        out.println("What positive number would you like to approximate? ");
        double mu = getPositiveDouble(in, out);
        out.print("This program will try to approximate ");
        out.println(mu, 2, false);

        out.println("Please provide four positive numbers that aren't one.");

        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        out.print("w ");
        out.print(w, 2, false);
        out.print(" x ");
        out.print(x, 2, false);
        out.print(" y ");
        out.print(y, 2, false);
        out.print(" z ");
        out.println(z, 2, false);

        double[] guessInfo = deJager(w, x, y, z, mu);

        out.print("Best guess is ");
        out.print(guessInfo[0], 2, false);
        out.print(" with an error of ");
        out.print(guessInfo[1], 2, false);
        out.println("%.");

        // a b c and d being recalled from the generated array
        out.print("exponents used were a: ");
        out.print(guessInfo[2], 2, false);
        out.print(" b: ");
        out.print(guessInfo[2 + 1], 2, false);
        out.print(" c: ");
        out.print(guessInfo[2 + 2], 2, false);
        out.print(" d: ");
        out.print(guessInfo[guessInfo.length - 1], 2, false);

        in.close();
        out.close();

    }
}
