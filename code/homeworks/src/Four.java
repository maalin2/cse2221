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

public final class Four {
    /**
     * No argument constructor--private to prevent instantiation.
     */

    private Four() {
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

        out.println("please provide a number with multiple digits");
        String number = in.nextLine();

        int i = 0;
        int j = number.length();
        int sum = 0;
        int digit = 0;

        while (i < j) {
            //add ot the sum the curent digit
            digit = Integer.parseInt(number.substring(j - i, j - i + 1));

            sum += digit;
            i += 2;
        }

        out.println(sum);
        in.close();
        out.close();

    }
}
