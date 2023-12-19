import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Homework from 10/23.
 *
 * @author M. Maalin
 */
public final class Review3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Review3() {
        // no code needed here
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        // divide by 10
        // return that number into a multiplier
        // if there are no more numbers return product

        NaturalNumber product = new NaturalNumber2("1");
        NaturalNumber nCopy = new NaturalNumber2(n);
        int lastDigit = nCopy.divideBy10();
        if (nCopy.isZero()) {
            nCopy.transferFrom(n);
            NaturalNumber digit = new NaturalNumber2(lastDigit);
            product.transferFrom(digit);
        } else {
            int multiplyDigit = n.divideBy10();
            NaturalNumber tmpProduct = new NaturalNumber2(multiplyDigit);
            tmpProduct.multiply(productOfDigits1(n));
            product.copyFrom(tmpProduct);
        }

        return product;
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        NaturalNumber nCopy = new NaturalNumber2(n);
        int lastDigit = nCopy.divideBy10();
        if (nCopy.isZero()) {
            NaturalNumber digit = new NaturalNumber2(lastDigit);
            n.transferFrom(digit);
        } else {
            NaturalNumber tmpProduct = new NaturalNumber2(n.divideBy10());
            tmpProduct.multiply(productOfDigits1(n));
            n.copyFrom(tmpProduct);
        }
        return n;
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        assert n.canConvertToInt() : "Violation of n <= Integer.MAX_VALUE";
        int nInt = 1;

        NaturalNumber nCopy = new NaturalNumber2(n);
        int lastDigit = nCopy.divideBy10();
        if (nCopy.isZero()) {
            nInt = n.divideBy10();
        } else {
            n.divideBy10();
            nInt = 10 * toInt(n) + lastDigit;
        }
        return nInt;
    }

    /**
     * Reports whether the given tag appears in the given {@code XMLTree}.
     *
     * @param xml
     *            the {@code XMLTree}
     * @param tag
     *            the tag name
     * @return true if the given tag appears in the given {@code XMLTree}, false
     *         otherwise
     * @ensures <pre>
     * findTag =
     *    [true if the given tag appears in the given {@code XMLTree}, false otherwise]
     * </pre>
     */
    private static boolean findTag(XMLTree xml, String tag) {
        boolean found = false;
        if (xml.isTag()) {
            if (xml.label().equals(tag)) {
                found = true;
            } else if (xml.numberOfChildren() > 0) {
                int children = xml.numberOfChildren() - 1;
                XMLTree subtree = xml.child(children);
                if (children >= 0 && !found) {
                    subtree = xml.child(children);
                    found = findTag(subtree, tag);
                    children--;
                }
            }
        }
        return found;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber test = new NaturalNumber2("5");

        int integerTest = toInt(test);
        out.println(integerTest);
//        out.println(productOfDigits1(test));
        //productOfDigits2(test);
        //       out.println(test);
        XMLTree testRSS = new XMLTree1("https://sports.yahoo.com/rss/");
        out.println(findTag(testRSS, "guid"));
        out.close();
    }

}
