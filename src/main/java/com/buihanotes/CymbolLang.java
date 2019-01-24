package com.buihanotes;

import com.buihanotes.ST.GlobalScope;
import com.buihanotes.grammars.CymbolLexer;
import com.buihanotes.grammars.CymbolParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class CymbolLang {
    public static void main(String[] args) throws IOException {
        CharStream input = null;
        if (args.length > 0) {
            System.out.println(args[0]);
            input = CharStreams.fromFileName(args[0]);
        } else {
            input = CharStreams.fromStream(System.in);
        }
        CymbolLexer lexer = new CymbolLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        CymbolParser parser = new CymbolParser(token);
        ParseTreeWalker walker = new ParseTreeWalker();
        GlobalScope globalScope = new GlobalScope();
        CymbolParserListener symListener = new CymbolParserListener(globalScope);
        ParseTree tree = parser.compilationUnit();
        walker.walk(symListener, tree);
        System.out.println("globals: " + globalScope.getSymbols());
    }
}
