import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private RSSProcessing() {
    }

    /**
     * prints out some stuff.
     *
     * @param out
     *            The stream to output html code to. This would be a file
     *            specified in the main function.
     * @param title
     *            What to print out under the paragraph tags.
     * @requires the two parameters
     * @ensures out = #out * well-formatted HTML code
     */
    private static void htmlPrinter(SimpleWriter out, String title) {
        out.println("<p>" + title + "</p>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml.isTag() : "Violation of contract: " + xml + "must be a tag";

        String tagInput = "tagInput";

        int i = 0;
        int indexOfChild = -1;

        while (i < xml.numberOfChildren()) {
            tagInput = xml.child(i).label();

            if (tagInput.equals(tag)) {
                indexOfChild = i;
            }

            i++;
        }

        return indexOfChild;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L("rss_reader.html");
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        XMLTree xml = new XMLTree1("https://news.yahoo.com/rss/");

        XMLTree channel = xml.child(getChildElement(xml, "channel"));

        XMLTree title = channel.child(getChildElement(channel, "title"));

        String titleVal = title.label();

        htmlPrinter(out, titleVal);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
