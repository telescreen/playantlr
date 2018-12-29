package com.buihanotes;

import com.buihanotes.grammars.CSVLexer;
import com.buihanotes.grammars.CSVParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCSVLoader {
    @Test
    @DisplayName("Test CSV Loader")
    void testCSVLoader() {
        CharStream charStream = null;
        try {
            charStream = CharStreams.fromFileName("src/test/resources/examples/t.csv");
        } catch(IOException e) {
            System.err.println("File not found");
            System.exit(-1);
        }
        CSVLexer lexer = new CSVLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        ParseTree tree = parser.file();
        CSVLoader loader = new CSVLoader();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(loader, tree);

        List<Map<String, String>> expected = new LinkedList<Map<String, String>>();
        Map<String, String> l = new LinkedHashMap<>();
        l.put("Details", "Mid Bonus"); l.put("Month", "June"); l.put("Amount", "\"$2,000\"");
        expected.add(l);
        l = new LinkedHashMap<>();
        l.put("Details", ""); l.put("Month", "January"); l.put("Amount", "\"\"\"zippo\"\"\"");
        expected.add(l);
        l = new LinkedHashMap<>();
        l.put("Details", "Total Bonuses"); l.put("Month", "\"\""); l.put("Amount", "\"$5,000\"");
        expected.add(l);

        assertTrue(expected.equals(loader.rows));
    }
}
