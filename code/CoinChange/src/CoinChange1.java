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
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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

        out.print("How many cents would you like to make change for? ");
        int cents = in.nextInteger();

        out.println("You have " + cents + " cents..");

        int dollars = 0;
        int halfDollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        final int dollar = 100;
        final int halfDollar = 50;
        final int quarter = 25;
        final int dime = 10;
        final int nickel = 5;

        if (cents > dollar) {
            dollars = cents / dollar;
            cents = cents % dollar;
        }

        if (cents > halfDollar) {
            halfDollars = cents / halfDollar;
            cents = cents % halfDollar;
        }

        if (cents > quarter) {
            quarters = cents / quarter;
            cents = cents % quarter;
        }

        if (cents > dime) {
            dimes = cents / dime;
            cents = cents % dime;
        }

        if (cents > nickel) {
            nickels = cents / nickel;
            cents = cents % nickel;
        }

        pennies = cents;

        out.println("This would take " + dollars + " dollars");
        out.println("This would take " + halfDollars + " half dollars");
        out.println("This would take " + quarters + " quarters");
        out.println("This would take " + dimes + " dimes");
        out.println("This would take " + nickels + " nickels");
        out.println("This would take " + pennies + " pennies");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
