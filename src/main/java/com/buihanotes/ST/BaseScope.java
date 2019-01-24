package com.buihanotes.ST;

import java.util.HashMap;
import java.util.Map;

public class BaseScope implements Scope {

    private String name;

    private Scope enclosingScope;

    private Map<String, Symbol> symbols = new HashMap<>();

    public BaseScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    public BaseScope(String name, Scope enclosingScope) {
        this.name = name;
        this.enclosingScope = enclosingScope;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    @Override
    public String getScopeName() {
        return name;
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    @Override
    public void define(Symbol sym) {
        symbols.put(sym.getName(), sym);
    }

    @Override
    public Symbol resolve(String name) {
        Symbol sym = symbols.get(name);
        if (sym == null && enclosingScope != null) {
            return enclosingScope.resolve(name);
        }
        return sym;
    }

    @Override
    public String toString() {
        return getScopeName() + ":" + symbols;
    }
}
