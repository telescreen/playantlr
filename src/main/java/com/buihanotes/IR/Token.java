package com.buihanotes.IR;

public class Token {
    public static final int INVALID_TOKEN_TYPE = 0;
    public static final int PLUS = 1; // token types
    public static final int INT = 2;

    public int type;
    public String text;

    public Token(int type) {
        this.type = type;
    }

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
