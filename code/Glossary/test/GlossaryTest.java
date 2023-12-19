import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue1L;

public class GlossaryTest {

    /**
     * Test cases for addLinkstoDefinition.
     */

    //routine case
    @Test
    public void testaddLinkstoDefinition_hi() {
        String def = "hi is a greeting";
        Queue1L<String> terms = new Queue1L<>();
        terms.enqueue("hello");

        String defExpected = "hi is a greeting";
        Queue1L<String> termsExpected = new Queue1L<>();
        termsExpected.enqueue("hello");

        assertEquals(def, defExpected);
        assertEquals(terms, termsExpected);
        assertEquals("hi is a greeting",
                Glossary.addLinkstoDefinition(def, terms));

    }

    //challenging/boundary case
    @Test
    public void testaddLinkstoDefinition_space() {
        String def = " ";
        Queue1L<String> terms = new Queue1L<>();
        terms.enqueue(" ");

        String defExpected = " ";
        Queue1L<String> termsExpected = new Queue1L<>();
        termsExpected.enqueue(" ");

        assertEquals(def, defExpected);
        assertEquals(terms, termsExpected);
        assertEquals("<a href=\"" + def + ".html\">" + def + "</a>",
                Glossary.addLinkstoDefinition(def, terms));
    }

}
