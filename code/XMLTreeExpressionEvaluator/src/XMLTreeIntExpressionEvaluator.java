import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 * Evaluating XMLTree math expressions with integers.
 *
 * @author Mohammed Maalin
 *
 */

public final class XMLTreeIntExpressionEvaluator {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        SimpleWriter log = new SimpleWriter1L();

        int answer = -1;

        if (exp.numberOfChildren() == 1) {
            log.println("hi");
            answer = Integer.parseInt(exp.attributeValue("value"));
        }

        if (exp.numberOfChildren() == 2) {
            XMLTree left = exp.child(0);
            XMLTree right = exp.child(1);

            int leftNum = 0;
            int rightNum = 0;

            if (left.label().equals("number")
                    && right.label().equals("number")) {
                leftNum = Integer.parseInt(left.attributeValue("value"));
                rightNum = Integer.parseInt(right.attributeValue("value"));
            } else if (left.label().equals("number")) {
                leftNum = Integer.parseInt(left.attributeValue("value"));
                rightNum = evaluate(right);
            } else if (right.label().equals("number")) {
                leftNum = evaluate(left);
                rightNum = Integer.parseInt(right.attributeValue("value"));
            } else {
                leftNum = evaluate(left);
                rightNum = evaluate(right);
            }

            String operation = exp.label();
            if (operation.equals("times")) {
                answer = leftNum * rightNum;
            } else if (operation.equals("divide")) {
                if (rightNum == 0) {
                    Reporter.fatalErrorToConsole("Error: division by 0");
                }
                answer = leftNum / rightNum;
            } else if (operation.equals("plus")) {
                answer = leftNum + rightNum;
            } else if (operation.equals("minus")) {
                answer = leftNum - rightNum;

            }
        }

        log.close();
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
        int solution = evaluate(exp);
        out.println(solution);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }
}
