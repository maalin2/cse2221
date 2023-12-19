import static org.junit.Assert.assertEquals;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Test cases for homework 14.
 *
 * @author M Maalin
 *
 */
public final class ToStringWithCommasTest {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ToStringWithCommasTest() {
    }

    /**
     * Converts the given {@code NaturalNumber} to a {@code String} with commas.
     *
     * @param n
     *            the number
     * @return the {@code String} with commas
     * @ensures toStringWithCommas = [String representation of n with commas]
     */
    public static String toStringWithCommas(NaturalNumber n) {
        return "Placeholder string to avoid errors";
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas13523() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(13523);
        NaturalNumber nExpected = new NaturalNumber2(13523);
        String str = toStringWithCommas(n);
        String strExpected = "13,523";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas981() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(981);
        NaturalNumber nExpected = new NaturalNumber2(981);
        String str = toStringWithCommas(n);
        String strExpected = "981";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas3235252() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(3235252);
        NaturalNumber nExpected = new NaturalNumber2(3235252);
        String str = toStringWithCommas(n);
        String strExpected = "3,235,252";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas0001() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(0001);
        NaturalNumber nExpected = new NaturalNumber2(0001);
        String str = toStringWithCommas(n);
        String strExpected = "0001";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas0000001() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(0000001);
        NaturalNumber nExpected = new NaturalNumber2(0000001);
        String str = toStringWithCommas(n);
        String strExpected = "0000001";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas0100() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(0100);
        NaturalNumber nExpected = new NaturalNumber2(0100);
        String str = toStringWithCommas(n);
        String strExpected = "0100";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas999() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(999);
        NaturalNumber nExpected = new NaturalNumber2(999);
        String str = toStringWithCommas(n);
        String strExpected = "999";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas1000() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(1000);
        NaturalNumber nExpected = new NaturalNumber2(1000);
        String str = toStringWithCommas(n);
        String strExpected = "1,000";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * @Test(timeout=100)
     */
    public void testToStringWithCommas0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        String str = toStringWithCommas(n);
        String strExpected = "0";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(strExpected, str);
    }

    /**
     * test cases:
     *
     * routine: 13523 -> 13,523 981 -> 981 3235252 -> 3,235,252
     *
     * all three of these cases are cases that would be typical inputs for the
     * function
     *
     * challenging cases:
     *
     * 0001 -> 0001. 0000001 -> 0000001 0100 -> 0100
     *
     * these three cases deal with numbers with multiple numbers of digits that
     * would have commas but since the digits are 0 the behavior should be a bit
     * different
     *
     * boundary cases:
     *
     * 999 -> 999 this case works well because it is right before the first
     * comma
     *
     * 1000 -> 1,000 this case works because it is where the first comma shows
     * up
     *
     * 0 -> 0 this is the smallest possible natural number, which is important
     * because its the type we are testing
     *
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        in.close();
        out.close();
    }

}
