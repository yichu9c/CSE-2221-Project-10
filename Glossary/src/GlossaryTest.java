import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * Junit test fixture to test glossary project.
 *
 * @author Yihone Chu
 *
 */
public class GlossaryTest {
    /**
     * Method to test whether all elements are in the sequence.
     */
    public static void allElementsAreContained() {

    }

    @Test //Test edge
    public void mapAndQueueTest2() {
        SimpleReader inFile = new SimpleReader1L("data/test2.txt");
        Map<String, String> mapTested = new Map1L<>();
        Sequence<String> termsTested = new Sequence1L<>();
        Glossary.mapAndQueue(inFile, mapTested, termsTested);

        Map<String, String> mapExpected = new Map1L<>();
        Sequence<String> termsExpected = new Sequence1L<>();

        assertEquals(mapExpected, mapTested);
        assertEquals(termsExpected, termsTested);

    }

    @Test //Test routine
    public void mapAndQueueTest1() {
        SimpleReader inFile = new SimpleReader1L("data/test1.txt");
        Map<String, String> mapTested = new Map1L<>();
        Sequence<String> termsTested = new Sequence1L<>();
        Glossary.mapAndQueue(inFile, mapTested, termsTested);

        Map<String, String> mapExpected = new Map1L<>();
        Sequence<String> termsExpected = new Sequence1L<>();

        mapExpected.add("Yihone Chu", "my name");
        mapExpected.add("RPAC", "rec center at osu");
        mapExpected.add("Food", "essential for life");

        termsExpected.add(0, "Yihone Chu");
        termsExpected.add(0, "RPAC");
        termsExpected.add(0, "Food");

        assertEquals(mapExpected, mapTested);
        assertEquals(termsExpected, termsTested);
    }

    @Test
    public void sortSequenceTest1() {
        Sequence<String> termsTested = new Sequence1L<>();
        termsTested.add(0, "yihone");
        termsTested.add(0, "hello");
        termsTested.add(0, "alphabet");
        Glossary.sortSequence(termsTested);

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "alphabet");
        termsExpected.add(1, "hello");
        termsExpected.add(2, "yihone");

        assertEquals(termsExpected, termsTested);
    }

    @Test //Tests duplicate scenario, should work though glossary has no duplicate
    public void sortSequenceTest2() {
        Sequence<String> termsTested = new Sequence1L<>();
        termsTested.add(0, "alphabet");
        termsTested.add(0, "alphabet");
        termsTested.add(0, "yihone");
        termsTested.add(0, "hello");
        Glossary.sortSequence(termsTested);

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "alphabet");
        termsExpected.add(1, "alphabet");
        termsExpected.add(2, "hello");
        termsExpected.add(3, "yihone");

        assertEquals(termsExpected, termsTested);
    }

    @Test //Test challenging case
    public void sortSequenceTest3() {
        Sequence<String> termsTested = new Sequence1L<>();
        termsTested.add(0, "alphabet");
        termsTested.add(0, "alpha");
        termsTested.add(0, "alphabe");

        Glossary.sortSequence(termsTested);

        Sequence<String> termsExpected = new Sequence1L<>();
        termsExpected.add(0, "alpha");
        termsExpected.add(1, "alphabe");
        termsExpected.add(2, "alphabet");

        assertEquals(termsExpected, termsTested);
    }

    @Test //Test edge
    public void containsTest1() {
        Sequence<String> terms = new Sequence1L<>();
        String str = "yihone";

        boolean real = Glossary.contains(str, terms);
        boolean expected = false;

        assertEquals(expected, real);
    }

    @Test //Test routine
    public void containsTest2() {
        Sequence<String> terms = new Sequence1L<>();
        String str = "";

        boolean real = Glossary.contains(str, terms);
        boolean expected = false;

        assertEquals(expected, real);
    }

    @Test //Test routine
    public void containsTest3() {
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "");
        String str = "";

        boolean real = Glossary.contains(str, terms);
        boolean expected = true;

        assertEquals(expected, real);
    }

    @Test //Test routine
    public void containsTest4() {
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "hello");
        String str = "";

        boolean real = Glossary.contains(str, terms);
        boolean expected = false;

        assertEquals(expected, real);
    }

    @Test // Test edge
    public void createIndexTest1() {
        Sequence<String> terms = new Sequence1L<>();
        String folder = "test";

        Glossary.createIndex(folder, terms);

        SimpleReader inFile = new SimpleReader1L(folder + "/index.html");
        String expected = "<html>\n  <head>\n    <title>Sample Glossary</title>\n  </head>\n  <body>\n    <h2>Sample Glossary</h2>\n    <hr />\n    <h3>Index</h3>\n    <ul>\n    </ul>\n  </body>\n</html>\n";

        String real = "";
        while (!inFile.atEOS()) {
            String temp = inFile.nextLine() + "\n";
            real += temp;
        }

        assertEquals(expected, real);

    }

    @Test //Test routine
    public void createIndexTest2() {
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "alphabet");
        terms.add(1, "cool");
        terms.add(2, "yihone");
        String folder = "test";

        Glossary.createIndex(folder, terms);

        SimpleReader inFile = new SimpleReader1L(folder + "/index.html");
        String expected = "<html>\n  <head>\n    <title>Sample Glossary</title>\n  </head>\n  <body>\n    <h2>Sample Glossary</h2>\n    <hr />\n    <h3>Index</h3>\n    <ul>\n      <li><a href =\"./alphabet.html\">alphabet</a></li>\n      <li><a href =\"./cool.html\">cool</a></li>\n      <li><a href =\"./yihone.html\">yihone</a></li>\n    </ul>\n  </body>\n</html>\n";

        String real = "";
        while (!inFile.atEOS()) {
            String temp = inFile.nextLine() + "\n";
            real += temp;
        }

        assertEquals(expected, real);
    }

    @Test //Test edge
    public void createTermsTest1() {
        Map<String, String> glossaryMap = new Map1L<>();
        Sequence<String> terms = new Sequence1L<>();
        String output = "data";

        for (int i = 0; i < glossaryMap.size(); i++) {

            String expected = "<html>\n  <head>\n    <title>" + terms.entry(i)
                    + "</title>\n  </head>\n  <body>\n    <h2><b><i><font color =\"red\">"
                    + terms.entry(i) + "</font></i></b></h2>\n    <blockquote>"
                    + glossaryMap.value(terms.entry(i)) + "<a href =\"./"
                    + terms.entry(i) + ".html\">" + terms.entry(i)
                    + "</a></blockquote>\n    <hr />\n    <p>Return to <a href=\">"
                    + output + "</a>.</p>\n  </body>\n</html>";

            String real = "";
            SimpleReader inFile = new SimpleReader1L(
                    output + "/" + terms.entry(i) + ".html");

            while (!inFile.atEOS()) {
                real += inFile.nextLine();
            }

            assertEquals(expected, real);
        }
    }

    @Test //Test edge
    public void createTermsTest2() {
        Map<String, String> glossaryMap = new Map1L<>();
        glossaryMap.add("alphabet", "all 26 letters");
        glossaryMap.add("Yihone Chu", "author of this program");
        Sequence<String> terms = new Sequence1L<>();
        terms.add(0, "alphabet");
        terms.add(1, "Yihone Chu");
        String output = "data";

        Glossary.createTerms(glossaryMap, terms, output);

        for (int i = 0; i < glossaryMap.size(); i++) {

            String expected = "<html>\n  <head>\n    <title>" + terms.entry(i)
                    + "</title>\n  </head>\n  <body>\n    <h2><b><i><font color=\"red\">"
                    + terms.entry(i) + "</font></i></b></h2>\n    <blockquote>"
                    + glossaryMap.value(terms.entry(i)) + "<a href =\"./"
                    + terms.entry(i) + ".html\">" + terms.entry(i)
                    + "</a></blockquote>\n    <hr />\n    <p>Return to <a href=\">"
                    + output + "</a>.</p>\n  </body>\n</html>";

            String real = "";
            SimpleReader inFile = new SimpleReader1L(
                    output + "/" + terms.entry(i) + ".html");

            while (!inFile.atEOS()) {
                real += inFile.nextLine() + "\n";
            }

            assertEquals(expected, real);
        }

    }

}
