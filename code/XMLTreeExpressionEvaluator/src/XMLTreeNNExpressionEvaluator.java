import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Evaluating XMLTree math expressions with NaturalNumber.
 *
 * @author Mohammed Maalin
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        // compute left tree __ right tree
        // if both are numbers then compute operation
        // if one of them are numbers then compute operation on both but run
        // the other tree recursively

        NaturalNumber answer = new NaturalNumber2("0");

        if (exp.numberOfChildren() == 0) {
            NaturalNumber number = new NaturalNumber2(
                    Integer.parseInt(exp.label()));

            answer.transferFrom(number);
        }

        if (exp.numberOfChildren() == 2) {
            XMLTree left = exp.child(0);
            XMLTree right = exp.child(1);

            NaturalNumber leftNum = new NaturalNumber2(0);
            NaturalNumber rightNum = new NaturalNumber2(0);

            if (left.label().equals("number")
                    && right.label().equals("number")) {

                NaturalNumber tmpLeft = new NaturalNumber2(
                        Integer.parseInt(left.attributeValue("value")));
                leftNum.transferFrom(tmpLeft);
                NaturalNumber tmpRight = new NaturalNumber2(
                        Integer.parseInt(right.attributeValue("value")));
                rightNum.transferFrom(tmpRight);
            } else if (left.label().equals("number")) {
                NaturalNumber tmpLeft = new NaturalNumber2(
                        Integer.parseInt(left.attributeValue("value")));
                leftNum.transferFrom(tmpLeft);
                rightNum = evaluate(right);
            } else if (right.label().equals("number")) {
                leftNum = evaluate(left);
                NaturalNumber tmpRight = new NaturalNumber2(
                        Integer.parseInt(right.attributeValue("value")));
                rightNum.transferFrom(tmpRight);
            } else {
                leftNum = evaluate(left);
                rightNum = evaluate(right);
            }

            String operation = exp.label();
            if (operation.equals("times")) {
                leftNum.multiply(rightNum);
            } else if (operation.equals("divide")) {
                if (rightNum.isZero()) {
                    Reporter.fatalErrorToConsole("Error: division by zero");
                }
                leftNum.divide(rightNum);
            } else if (operation.equals("plus")) {
                leftNum.add(rightNum);
            } else if (operation.equals("minus")) {
                if (rightNum.compareTo(leftNum) > 0) {
                    Reporter.fatalErrorToConsole(
                            "Error: subtraction would result in negative number");
                }

                leftNum.subtract(rightNum);
            }
            answer.copyFrom(leftNum);
        }

        return answer;
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

        out.print("Please enter a valid xml expression: ");
        String xmlPath = in.nextLine();
        XMLTree xml = new XMLTree2(xmlPath);
        XMLTree exp = xml.child(0);
        out.println(exp.toString());
        NaturalNumber solution = evaluate(exp);
        out.println(solution);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
