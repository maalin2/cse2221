import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
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

        String[] coinNames = { "dollars", "half dollars", "quarters", "dimes",
                "nickels" };

        final int dollar = 100;
        final int halfDollar = 50;
        final int quarter = 25;
        final int dime = 10;
        final int nickel = 5;

        int[] coinValues = { dollar, halfDollar, quarter, dime, nickel };
        int[] coins = { 0, 0, 0, 0, 0, 0 };

        out.print("How many cents would you like to make change for? ");
        int cents = in.nextInteger();

        out.println("You have " + cents + " cents..");

        final int numOfCoins = 5;

        for (int i = 0; i < numOfCoins; i++) {
            if (cents > coinValues[i]) {
                coins[i] = cents / coinValues[i];
                out.println("This would take " + coins[i] + " " + coinNames[i]);
                cents = cents % coinValues[i];
            }
        }
        out.print("This would take " + cents + " pennies");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
