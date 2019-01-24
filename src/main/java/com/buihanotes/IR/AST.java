package com.buihanotes.IR;

import java.util.ArrayList;
import java.util.List;

public class AST {
    private Token token;
    protected List<AST> childrens;

    public AST() {
        // Default constructor for NULL Token
    }

    public AST(Token token) {
        this.token = token;
    }

    public AST(int tokenType) {
        this.token = new Token(tokenType);
    }

    public int getNodeType() {
        return token.type;
    }

    public void addChild(AST t) {
        if (childrens == null) childrens = new ArrayList<>();
        childrens.add(t);
    }

    public boolean isNil() {
        return (token == null);
    }

    @Override
    public String toString() {
        return token != null ? token.toString() : "nil";
    }

    public String toStringTree() {
        if (childrens == null || childrens.size() == 0) {
            return this.toString();
        }
        StringBuilder builder = new StringBuilder();
        if (!isNil()) {
            builder.append("(");
            builder.append(this.toString());
            builder.append(" ");
        }
        for (int i = 0; i < childrens.size(); ++i) {
            AST t = (AST)childrens.get(i);
            if (i > 0) builder.append(" ");
            builder.append(t.toStringTree());
        }
        if (!isNil()) {
            builder.append(")");
        }
        return builder.toString();
    }
}
