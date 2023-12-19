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
public final class LabSep22 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private LabSep22() {
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

        final double microsPerDay = ((24.0 * 60) * (60 * 1000) * 1000);
        final double millisPerDay = (24 * 60) * (60 * 1000);
        out.println(microsPerDay / millisPerDay);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
