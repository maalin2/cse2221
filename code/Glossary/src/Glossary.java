import components.map.Map;
import components.map.Map1L;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Generates glossary webpage of words from a text file.
 *
 * @author Mohammed Maalin
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int end = position;
        boolean isSeparator = separators.contains(text.charAt(position));
        while (end < text.length()
                && isSeparator == separators.contains(text.charAt(end))) {
            end++;
        }
        return text.substring(position, end);

    }

    /**
     * Processes file and returns a map with the terms and definitions.
     *
     * @param file
     *            the file to process
     *
     * @requires that the file exists
     *
     * @ensures a map with the keys and values generated from the first
     *          non-empty line and the second non-empty line respectively
     * @return map with keys and values as words and definitions
     */
    public static Map1L<String, String> processFile(SimpleReader file) {
        Map1L<String, String> words = new Map1L<>();

        while (!file.atEOS()) {
            String definition = "";
            String temp = file.nextLine();
            String word = temp;

            if (!temp.equals("") && !temp.contains(" ")) {
                word = temp;

                while (!temp.equals("")) {
                    temp = file.nextLine();
                    definition += temp;
                }
            }
            words.add(word, definition);
        }

        return words;
    }

    /**
     * Generates a queue of words sorted alphabetically.
     *
     * @param words
     *            a map with words to sort and their definitions
     *
     * @requires |words| = 2
     * @ensures sortWords = keys of words sorted alphabetically
     * @return a sorted queue of the glossary's terms
     */
    public static Queue1L<String> sortWords(Map1L<String, String> words) {
        Queue1L<String> wordQueue = new Queue1L<>();

        for (Map.Pair<String, String> word : words) {
            wordQueue.enqueue(word.key());
        }

        wordQueue.sort(String.CASE_INSENSITIVE_ORDER);

        return wordQueue;
    }

    /**
     * Goes through every word in a term's definition and adds HTML code for
     * links to pages of terms in the definition.
     *
     * @param def
     *            the definition for a term
     * @param terms
     *            a queue of all of the terms
     * @requires |def| is not empty, |terms| is not empty
     * @ensures defWithLinks = def * [HTML code to link to other terms]
     * @return the definition updated with links to other terms in the
     *         definition
     */
    public static String addLinkstoDefinition(String def,
            Queue1L<String> terms) {
        String defWithLinks = "";

        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add(' ');
        separators.add('\n');

        int position = 0;
        while (position < def.length()) {
            String word = nextWordOrSeparator(def, position, separators);

            boolean isTerm = false;
            for (String wordToCheck : terms) {
                if (word.equals(wordToCheck)) {
                    isTerm = true;
                }
            }
            if (isTerm) {
                defWithLinks += "<a href=\"" + word + ".html\">" + word
                        + "</a>";
            } else {
                defWithLinks += (word);
            }

            position += word.length();
        }
        return defWithLinks;
    }

    /**
     * Generates HTML code for an entry in the glossary.
     *
     * @param word
     *            the word to generate a page for
     * @param words
     *            a map with terms and definitions
     * @param wordQueue
     *            a queue of all of the terms to be in the glossary
     * @requires word, words, and wordQueue are not empty
     * @ensures generateEntryHTML is valid HTML code that creates an entry in
     *          the web glossary
     * @return a string of HTML code to print to a file
     */
    public static String generateEntryHTML(String word,
            Map1L<String, String> words, Queue1L<String> wordQueue) {
        String html = "";

        String def = words.value(word);

        html += ("<html>");
        html += ("<head>");
        html += ("<title>" + word + "</title>");
        html += ("</head>");
        html += ("<body>");
        html += "<h2><b><i><font color=\"red\">" + word
                + "</font></i></b></h2>";
        html += ("<blockquote>" + addLinkstoDefinition(def, wordQueue)
                + "</blockquote>");
        html += ("<hr />");
        html += ("<p>Return to <a href=\"index.html\">index</a>.</p>");
        html += ("</body>");
        html += ("</html>");

        return html;
    }

    /**
     * Generates an index page as well as entries for each of the terms of the
     * glossary.
     *
     * @param htmlPath
     *            the file path to create all of the HTML files
     * @param words
     *            a map of terms and definitions
     * @requires htmlPath exists on the computer
     * @ensures a web glossary with an index and pages for each of the terms is
     *          generated in the specified folder
     *
     */
    public static void generateHTML(String htmlPath,
            Map1L<String, String> words) {
        SimpleWriter index = new SimpleWriter1L(htmlPath + "/index.html");
        index.println("<html>");
        index.println("<head>");
        index.println("<title>Glossary</title>");
        index.println("</head>");
        index.println("<body>");
        index.println("<h1>Glossary</h1>");
        index.println("<hr />");
        index.println("<h2>Index</h2>");
        index.println("<ul>");

        Queue1L<String> wordList = sortWords(words);

        for (String word : wordList) {
            SimpleWriter page = new SimpleWriter1L(
                    htmlPath + "/" + word + ".html");
            page.print(generateEntryHTML(word, words, wordList));
            index.println(
                    "<li><a href='" + word + ".html'>" + word + "</a></li>");

            page.close();
        }

        index.println("</ul>");
        index.println("</body>");
        index.println("</html>");

        index.close();
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

        out.println("which glossary file do you want to process?");
        SimpleReader txt = new SimpleReader1L(in.nextLine());

        out.println("Which folder do you want to save html to");
        String htmlPath = in.nextLine();

        Map1L<String, String> words = processFile(txt);

        generateHTML(htmlPath, words);

        /*
         * Close input and output streams
         */

        txt.close();

        in.close();
        out.close();
    }

}
