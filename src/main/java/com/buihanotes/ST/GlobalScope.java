package com.buihanotes.ST;

public class GlobalScope extends BaseScope {
    public GlobalScope() {
        super("global", null);
        initTypeSystem();
    }

    private void initTypeSystem() {
        define(new BuiltInTypeSymbol("int"));
        define(new BuiltInTypeSymbol("float"));
        define(new BuiltInTypeSymbol("void"));
    }
}
