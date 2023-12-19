import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Unit tests for StringReassembly class.
 *
 */
public class StringReassemblyTest {

    /*
     * Tests of combination
     */

    /**
     * Boundary case.
     *
     */
    @Test
    public void testCombination_A() {
        String str1 = " ";
        String str1Expected = " ";
        String str2 = "a";
        String str2Expected = "a";
        int overlap = 0;
        int overlapExpected = 0;

        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(overlap, overlapExpected);
        assertEquals(StringReassembly.combination(str1, str2, overlap), " a");
    }

    /**
     * Challenging case.
     *
     */
    @Test
    public void testCombination_LongStrings() {
        String str1 = "oiahfoisahfoisahfoisahfoisahfoiahfoisahoioiahfoisahfoisa"
                + "hfoisahfoisahfoiahfoisahoioiahfoisahfoisahfoisahfoisahfoiah"
                + "foisahoifahsf";
        String str1Expected = "oiahfoisahfoisahfoisahfoisahfoiahfoisahoioiahfoi"
                + "sahfoisahfoisahfoisahfoiahfoisahoioiahfoisahfoisahfoisahfois"
                + "ahfoiahfoisahoifahsf"; // fahsf are in both strings
        String str2 = "fahsfafhaoishfoisahfoiahsfioahsfoiahsoifafshaf";
        String str2Expected = "fahsfafhaoishfoisahfoiahsfioahsfoiahsoifafshaf";
        int overlap = 5;
        int overlapExpected = 5;

        String combinationExpected = "oiahfoisahfoisahfoisahfoisahfoiahfoisahoi"
                + "oiahfoisahfoisahfoisahfoisahfoiahfoisahoioiahfoisahfoisahfo"
                + "isahfoisahfoiahfoisahoi" + str2;

        StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(overlap, overlapExpected);
        assertEquals(StringReassembly.combination(str1, str2, overlap),
                combinationExpected);

    }

    /**
     * Routine case.
     *
     */
    @Test
    public void testCombination_Spaces() {
        String str1 = "  ";
        String str1Expected = "  ";
        String str2 = " space";
        String str2Expected = " space";
        int overlap = 1;
        int overlapExpected = 1;

        StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(overlap, overlapExpected);
        assertEquals(StringReassembly.combination(str1, str2, overlap),
                "  space");
    }

    /*
     * Tests of overlap
     */

    @Test
    public void testOverlap_abc_defabg() {
        String str1 = "abc";
        String str1Expected = "abc";
        String str2 = "defabg";
        String str2Expected = "defabg";
        int overlap = StringReassembly.overlap(str1, str2);
        int overlapExpected = 0;

        StringReassembly.overlap(str1, str2);
        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(overlap, overlapExpected);

    }

    //commonly misspelled word
    @Test
    public void testOverlap_Apparent_apparent() {
        String str1 = "Apparent";
        String str1Expected = "Apparent";
        String str2 = "apparent";
        String str2Expected = "apparent";
        int overlap = StringReassembly.overlap(str1, str2);
        int overlapExpected = 0;

        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(overlap, overlapExpected);
    }

    @Test
    public void testOverlap_Ab_ba() {
        String str1 = "Ab";
        String str1Expected = "Ab";
        String str2 = "ba";
        String str2Expected = "ba";
        int overlap = StringReassembly.overlap(str1, str2);
        int overlapExpected = 1;

        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(overlap, overlapExpected);
    }

    /*
     * Tests for assemble
     */
    /**
     * Routine case.
     */
    @Test
    public void testAssemble_Yes_No_Maybe() {
        Set<String> strSet = new Set1L<>();
        strSet.add("yes");
        strSet.add("no");
        strSet.add("maybe");

        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("yes");
        strSetExpected.add("no");
        strSetExpected.add("maybe");

        StringReassembly.assemble(strSet);
        assertEquals(strSet, strSetExpected);
    }

    /**
     * Challenging case.
     */
    @Test
    public void testAssemble_123456789_Hi() {
        Set<String> strSet = new Set1L<>();
        strSet.add("_h");
        strSet.add("_123456789_");
        strSet.add("hi");

        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("_123456789_hi");

        StringReassembly.assemble(strSet);
        assertEquals(strSet, strSetExpected);
    }

    /**
     * Boundary case.
     */
    @Test
    public void testAssemble_123_345() {
        Set<String> strSet = new Set1L<>();
        strSet.add(" 123");
        strSet.add("345");

        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add(" 12345");

        StringReassembly.assemble(strSet);
        assertEquals(strSet, strSetExpected);
    }

    /*
     * Tests for printWithLineSeperators
     */
    /**
     * Boundary case.
     */
    @Test
    public void testPrintWithLineSeperators_Tilde() {
        SimpleWriter out = new SimpleWriter1L();
        SimpleWriter outExpected = new SimpleWriter1L();
        outExpected.println();

        String text = "~";
        String textExpected = "~";

        StringReassembly.printWithLineSeparators(text, out);

        assertEquals(text, textExpected);
        assertEquals(out.toString(), outExpected.toString());

        out.clear();
        out.close();
        outExpected.close();
    }

    /**
     * Routine case.
     */
    @Test
    public void testPrintWithLineSeperators_Hi_Goodmorning() {
        SimpleWriter out = new SimpleWriter1L();
        SimpleWriter outExpected = new SimpleWriter1L();
        outExpected.println("hi\ngoodmorning");

        String text = "hi~goodmorning";
        String textExpected = "hi~goodmorning";

        StringReassembly.printWithLineSeparators(text, out);

        assertEquals(text, textExpected);
        assertEquals(out.toString(), outExpected.toString());

        out.clear();
        out.close();
        outExpected.close();
    }

    /**
     * Challenging case.
     */
    @Test
    public void testPrintWithLineSeperators_Tildes_3() {
        SimpleWriter out = new SimpleWriter1L();
        SimpleWriter outExpected = new SimpleWriter1L();
        outExpected.println("\n\n\n\n\n\n\n3\n\n\n\n\n\n\n\n");

        String text = "~~~~~~~3~~~~~~~~";
        String textExpected = "~~~~~~~3~~~~~~~~";

        StringReassembly.printWithLineSeparators(text, out);

        assertEquals(text, textExpected);
        assertEquals(out.toString(), outExpected.toString());

        out.clear();
        out.close();
        outExpected.close();
    }

    /*
     * Tests for addToSetAvoidingSubstrings
     */

    /**
     * Boundary case.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_1() {
        Set<String> strSet = new Set1L<>();
        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("1");

        String text = "1";
        String textExpected = "1";

        StringReassembly.addToSetAvoidingSubstrings(strSet, text);

        assertEquals(text, textExpected);
        assertEquals(strSet, strSetExpected);
    }

    /**
     * Routine case.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_abc_def() {
        Set<String> strSet = new Set1L<>();
        strSet.add("abc");

        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("abc");
        strSetExpected.add("def");

        String str = "def";
        String strExpected = "def";

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);

        assertEquals(str, strExpected);
        assertEquals(strSet, strSetExpected);
    }

    /**
     * Challenging case.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_h_Ah() {
        Set<String> strSet = new Set1L<>();
        strSet.add("h");

        Set<String> strSetExpected = new Set1L<>();
        strSetExpected.add("A      h ");

        String str = "A      h ";
        String strExpected = "A      h ";

        StringReassembly.addToSetAvoidingSubstrings(strSet, str);

        assertEquals(str, strExpected);
        assertEquals(strSet, strSetExpected);
    }

    /*
     * Tests for bestOverlap
     */
    /**
     * Routine case.
     */
    @Test
    public void testBestOverlap_OSU_Michigan() {
        String str1 = "OSU";
        String str1Expected = "OSU";

        String str2 = "Michigan";
        String str2Expected = "Michigan";

        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(StringReassembly.overlap(str1, str2), 0);
    }

    /**
     * Boundary case.
     */
    @Test
    public void testBestOverlap_a_b() {
        String str1 = "a";
        String str1Expected = "a";

        String str2 = "b";
        String str2Expected = "b";

        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(StringReassembly.overlap(str1, str2), 0);
    }

    /**
     * Challenging case.
     */
    @Test
    public void testBestOverlap_Unicode() {
        String str1 = "Σqoiwyroiqwyroiqw";
        String str1Expected = "Σqoiwyroiqwyroiqw";

        String str2 = "s";
        String str2Expected = "s";

        assertEquals(str1, str1Expected);
        assertEquals(str2, str2Expected);
        assertEquals(StringReassembly.overlap(str1, str2), 0);
    }
}
