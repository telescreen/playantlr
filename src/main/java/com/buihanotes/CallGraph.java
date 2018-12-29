package com.buihanotes;

import com.buihanotes.grammars.CymbolBaseListener;
import com.buihanotes.grammars.CymbolLexer;
import com.buihanotes.grammars.CymbolParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.Set;

public class CallGraph {
    static class Graph {
        Set<String> nodes = new OrderedHashSet<>();
        MultiMap<String, String> edges = new MultiMap<>();
        public void edge(String source, String target) {
            edges.map(source, target);
        }

        public String toDot() {
            StringBuffer buf = new StringBuffer();
            buf.append("digraph G {\n");
            buf.append("  ranksep=.25\n");
            buf.append("  edge [arrowsize=.5]\n");
            buf.append("  node [shape=circle fontname=\"ArialNarrow\", \n");
            buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
            buf.append("  ");
            for(String node: nodes) {
                buf.append(node);
                buf.append(";");
            }
            buf.append("\n");
            for(String src: edges.keySet()) {
                for(String tgt: edges.get(src)) {
                    buf.append("  ");
                    buf.append(src);
                    buf.append(" -> ");
                    buf.append(tgt);
                    buf.append(";\n");
                }
            }
            buf.append("}\n");
            return buf.toString();
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "nodes=" + nodes +
                    ", edges=" + edges +
                    '}';
        }
    }

    static class CymbolListener extends CymbolBaseListener {
        Graph graph = new Graph();
        String currentFunctionName = null;

        @Override
        public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
            currentFunctionName = ctx.ID().getText();
            graph.nodes.add(currentFunctionName);
        }

        @Override
        public void exitCall(CymbolParser.CallContext ctx) {
            graph.edge(currentFunctionName, ctx.ID().getText());
        }
    }

    public static void main(String[] args) {
        try {
            CharStream stream = CharStreams.fromFileName("src/test/resources/examples/t.cymbol");
            CymbolLexer lexer = new CymbolLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CymbolParser parser = new CymbolParser(tokens);
            ParseTreeWalker walker = new ParseTreeWalker();
            ParseTree tree = parser.file();
            CymbolListener listener = new CymbolListener();
            walker.walk(listener, tree);
            System.out.println(listener.graph.toString());
            System.out.println(listener.graph.toDot());
        } catch (IOException e) {
            System.err.println("File not found");
            System.exit(-1);
        }
    }
}
