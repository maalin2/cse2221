import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Password checker.
 *
 * @author Mohammed Maalin
 *
 */
public final class PasswordChecker {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private PasswordChecker() {
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        int j = 0;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                j++;
            }
        }

        return j > 0;

    }

    /**
     * Checks if the given String contains an lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        int j = 0;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                j++;
            }
        }

        return j > 0;

    }

    /**
     * Checks if the given String contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if str contains a digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        int j = 0;

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                j++;
            }
        }

        return j > 0;

    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        final int goodLength = 7;

        int j = 0;

        if (passwordCandidate.length() > goodLength) {

            boolean[] trueTable = { containsUpperCaseLetter(passwordCandidate),
                    containsLowerCaseLetter(passwordCandidate),
                    containsDigit(passwordCandidate) };

            for (int i = 0; i < trueTable.length; i++) {
                if (trueTable[i]) {
                    j++;
                }
            }
        }

        if (j >= 2) {
            out.println("good");
        } else {
            out.println("bad");
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        // whats ur password call checkPassword

        output.print("Set your password: ");
        String newPassword = in.nextLine();
        checkPassword(newPassword, output);
        /*
         * Close input and output streams
         */
        in.close();
        output.close();
    }

}
