import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Methods from homework 12.
 *
 * @author Mohammed Maalin
 *
 */
public final class RecursionI {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private RecursionI() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        final NaturalNumber ten = new NaturalNumber1L("10");

        if (n.compareTo(ten) < 0) {
            return 1;
        } else {
            n.divideBy10();
            return 1 + numberOfDigits(n);
        }
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static int sumOfDigits(NaturalNumber n) {

        int sum = n.divideBy10();
        if (!n.isZero()) {

            sum += sumOfDigits(n);
        }
        return sum;

    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigitsNN(NaturalNumber n) {
        NaturalNumber sum = new NaturalNumber2(n.divideBy10());

        if (!sum.isZero()) {
            sum.add(sumOfDigitsNN(n));
        }

        return sum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        //final NaturalNumber one = new NaturalNumber2("1");

        SimpleWriter log = new SimpleWriter1L();
        int right = n.divideBy10();
        int left = n.divideBy10();
        int halfL = left / 2;
        if (!n.isZero()) {
            n.multiplyBy10(left);
            divideBy2(n);
            n.multiplyBy10(halfL);
        } else {
            //final int ten = 10;
            // stuck on this bit
            int halfR = 1 + (halfL + right - ((right + 1) / 2));
            n.multiplyBy10(halfL);
            n.multiplyBy10(halfR);
        }
        log.println(n);
        log.close();
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {
        //base cases: if length is 1 or less
        //if first character != last one

        if (s.length() <= 1) {
            return true;
        }

        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }

        String sSub = s.substring(1, s.length() - 1);
        return isPalindrome(sSub);
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

        NaturalNumber lol = new NaturalNumber2("90238");

        divideBy2(lol);

//        out.println(isPalindrome("racecar"));
//        out.println(isPalindrome("hello"));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
