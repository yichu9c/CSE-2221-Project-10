import components.map.Map;
import components.map.Map2;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Glossary Project.
 *
 * @author Yihone Chu
 *
 */
public final class Glossary {

    /**
     * Default Constructor.
     */
    private Glossary() {
        //Intentionally left empty
    }

    /**
     * Inputs a "glossary" of terms and their definitions from a given
     * SimpleReader and stores them in the given {@code Map} and
     * {@code Sequence}.
     *
     * @param input
     *            the input stream
     * @param glossaryMap
     *            the term -> definition map
     * @param terms
     *            the queue of terms
     * @replaces GlossaryMap
     * @replaces terms
     * @requires<pre>
     * [file named fileName exists but is not open, and has the
     *  format of the term on the first line and the definition on the
     *  corresponding lines until there is a empty line.
     * </pre>
     * @ensures [GlossaryMap contains term -> definition mapping from file
     *          fileName]
     */
    public static void mapAndQueue(SimpleReader input,
            Map<String, String> glossaryMap, Sequence<String> terms) {
        assert input != null : "Violation of: fileName is not null";
        assert glossaryMap != null : "Violation of: GlossaryMap is not null";
        assert input.isOpen() : "Violation of : Input is open";

        String termAndDef = "";
        while (!input.atEOS()) {

            String temp = input.nextLine();
            if (termAndDef.equals("") && !temp.equals("")) {
                termAndDef += temp + "|";
                terms.add(0, temp);
            } else if (!termAndDef.equals("") && !temp.equals("")) {
                termAndDef += temp;
            } else {

                String term = termAndDef.substring(0, termAndDef.indexOf("|"));
                String def = termAndDef.substring(termAndDef.indexOf("|") + 1);
                glossaryMap.add(term, def);
                termAndDef = "";
            }
        }
    }

    /**
     * Given a set of strings or "terms", sort the set in alphabetical order.
     *
     * @param terms
     *            The set that will be sorted
     * @updates terms
     * @requires <pre> terms is not null
     * </pre>
     * @ensures That the queue of terms are sorted in alphabetical order
     */

    public static void sortSequence(Sequence<String> terms) {
        assert terms != null : "Violation of terms is not null";

        for (int i = 0; i < terms.length(); i++) { //Holds each element
            for (int j = i + 1; j < terms.length(); j++) {
                String first = terms.remove(i);
                String second = terms.remove(j - 1);
                if (first.compareToIgnoreCase(second) > 0) {

                    String temp = first;
                    terms.add(i, second);
                    terms.add(j, temp);
                } else {
                    terms.add(i, first);
                    terms.add(j, second);
                }
            }
        }
    }

    /**
     * Determines whether the given "term" is in the sequence of "terms".
     *
     * @param term
     *            The given "term" as a string
     * @param terms
     *            sequence of "terms" as strings
     * @requires terms is not null and the string term is not null
     * @ensures<pre>
     * that true boolean is returned if the given term is in the
     * sequence of terms otherwise a false boolean is returned
     * </pre>
     * @return a boolean that is true or false depending on whether term is in
     *         the sequence terms
     */
    public static boolean contains(String term, Sequence<String> terms) {
        assert terms != null : "Violation of terms is not null";
        assert term != null : "Violation of term is not null";

        boolean contains = false;
        int i = 0;
        while (!contains && i < terms.length()) {
            String temp = terms.entry(i);
            if (term.equals(temp)) {
                contains = true;
            }
            i++;
        }
        return contains;
    }

    /**
     * Creates the index of the glossary.
     *
     * @param output
     *            the name of the folder in which output files should be stored
     * @param terms
     *            the sequence of the terms in alphabetical order
     * @requires output is not null
     * @requires the sequence terms is not null
     * @ensures<pre>
     * that he output stream is formatted correctly and contains links to all
     * of the terms.
     * </pre>
     */

    public static void createIndex(String output, Sequence<String> terms) {
        assert output != null : "Violation of output is not null";
        assert terms != null : "Violation of terms is not null";
        SimpleWriter outFile = new SimpleWriter1L(output + "/index.html");

        outFile.println("<html>");
        outFile.println("  <head>");
        outFile.println("    <title>Sample Glossary</title>");
        outFile.println("  </head>");
        outFile.println("  <body>");
        outFile.println("    <h2>Sample Glossary</h2>");
        outFile.println("    <hr />");
        outFile.println("    <h3>Index</h3>");
        outFile.println("    <ul>");
        for (String str : terms) {
            outFile.println("      <li><a href =\"./" + str + ".html\">" + str
                    + "</a></li>");
        }
        outFile.println("    </ul>");
        outFile.println("  </body>");
        outFile.println("</html>");
    }

    /**
     * Creates a html page for each term in the glossary.
     *
     * @param glossaryMap
     *            the map of "terms" and their corresponding "definitions"
     * @param terms
     *            the sequence of "terms" sorted in alphabetical order
     * @param output
     *            the name of the folder in which output files should be stored
     * @requires The map of "terms" and "definitions" is not null
     * @requires The sequence of "terms" is not null.
     * @ensures<pre> that a HTML page is created for each "term" in the
     * "glossary"</pre>
     */
    public static void createTerms(Map<String, String> glossaryMap,
            Sequence<String> terms, String output) {
        for (int i = 0; i < glossaryMap.size(); i++) {
            SimpleWriter out = new SimpleWriter1L(
                    output + "/" + terms.entry(i) + ".html");
            out.println("<html>");
            out.println("  <head>");
            out.println("    <title>" + terms.entry(i) + "</title>");
            out.println("  </head>");
            out.println("  <body>");
            out.println("    <h2><b><i><font color=\"red\">" + terms.entry(i)
                    + "</font></i></b></h2>");

            String toBePrinted = "";
            String[] defToArray = glossaryMap.value(terms.entry(i)).split(" ");

            for (int j = 0; j < defToArray.length; j++) {
                if (contains(defToArray[j], terms)) {
                    toBePrinted += "<a href=\"" + defToArray[j] + ".html\">"
                            + defToArray[j] + " </a>";
                } else {
                    toBePrinted += defToArray[j] + " ";
                }
            }
            out.println("    <blockquote>" + toBePrinted + "</blockquote>");

            out.println("    <hr />");
            out.println(
                    "    <p>Return to <a href=\"./index.html\">index</a>.</p>");
            out.println("  </body>");
            out.println("</html>");

            out.close();
        }
    }

    /**
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Input a valid input file: ");
        SimpleReader inFile = new SimpleReader1L(in.nextLine());

        out.print("Folder where output filed will be saved: ");
        String output = in.nextLine();

        Map<String, String> glossaryMap = new Map2<>();
        Sequence<String> terms = new Sequence1L<>();
        //Read in the file and put them into the map and sequence
        mapAndQueue(inFile, glossaryMap, terms);

        sortSequence(terms);

        createTerms(glossaryMap, terms, output);

        createIndex(output, terms);

        in.close();
        out.close();

    }

}
