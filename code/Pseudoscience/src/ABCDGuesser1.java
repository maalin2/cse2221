import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Using the de Jager formula to approximate a given number using four given
 * constants to a reasonable degree of accuracy.
 *
 * @author Mohammed Maalin
 *
 */
public final class ABCDGuesser1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
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

                if ((Math.abs(notOne - 1) > doubleError) && (notOne > 0)) {
                    i = 0;
                }
                // check if positive
            }

        }

        return notOne;
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

        int checkA = 0;
        int checkB = 0;
        int checkC = 0;
        int checkD = 0;

        final double percent = 100.0;

        while (checkA < exponents.length) {
            checkB = 0;
            while (checkB < exponents.length) {
                checkC = 0;
                while (checkC < exponents.length) {
                    checkD = 0;
                    while (checkD < exponents.length) {
                        guess = Math.pow(w, exponents[checkA])
                                * Math.pow(x, exponents[checkB])
                                * Math.pow(y, exponents[checkC])
                                * Math.pow(z, exponents[checkD]);

                        error = (percent * (Math.abs((guess - mu) / mu)));

                        if (error < bestError) {
                            bestError = error;
                            bestGuess = guess;

                            bestA = exponents[checkA];
                            bestB = exponents[checkB];
                            bestC = exponents[checkC];
                            bestD = exponents[checkD];
                        }
                        checkD++;
                    }
                    checkC++;
                }
                checkB++;
            }
            checkA++;
        }

        out.print("Best guess is ");
        out.print(bestGuess, 2, false);
        out.print(" with an error of ");
        out.print(bestError, 2, false);
        out.println("%.");

        out.print("exponents used were a: ");
        out.print(bestA, 2, false);
        out.print(" b: ");
        out.print(bestB, 2, false);
        out.print(" c: ");
        out.print(bestC, 2, false);
        out.print(" d: ");
        out.print(bestD, 2, false);

        in.close();
        out.close();

    }
}
