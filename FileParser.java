package coziahr;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses a text file with one number per line, returns a ParseResult with a LinkedList<Double>
 * and details about invalid lines.
 */
public class FileParser {

    public static class ParseResult {
        public final LinkedList<Double> list;
        public final int validLines;
        public final int invalidLinesCount;
        public final List<String> invalidLineContents;
        public final int emptyLinesCount;

        public ParseResult(LinkedList<Double> list, int validLines, int invalidLinesCount,
                           List<String> invalidLineContents, int emptyLinesCount) {
            this.list = list;
            this.validLines = validLines;
            this.invalidLinesCount = invalidLinesCount;
            this.invalidLineContents = invalidLineContents;
            this.emptyLinesCount = emptyLinesCount;
        }
    }

    public static ParseResult parseFile(File file) throws Exception {
        LinkedList<Double> list = new LinkedList<>();
        int valid = 0;
        int invalid = 0;
        int empty = 0;
        List<String> invalidContents = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                lineNo++;
                if (line.trim().isEmpty()) {
                    empty++;
                    continue;
                }
                try {
                    double value = Double.parseDouble(line.trim());
                    list.add(value);
                    valid++;
                } catch (NumberFormatException ex) {
                    invalid++;
                    invalidContents.add("Line " + lineNo + ": \"" + line + "\"");
                }
            }
        }
        return new ParseResult(list, valid, invalid, invalidContents, empty);
    }
}