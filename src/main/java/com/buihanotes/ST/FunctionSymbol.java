package com.buihanotes.ST;

import org.antlr.v4.misc.OrderedHashMap;

import java.util.HashMap;
import java.util.Map;

;

public class FunctionSymbol extends Symbol implements Scope {

    private OrderedHashMap<String, Symbol> orderedArgs = new OrderedHashMap<>();

    private Scope enclosingScope;

    private Map<String, Symbol> symbols = new HashMap<>();

    public FunctionSymbol(String name, Type type, Scope enclosingScope) {
        super(name, type);
        this.enclosingScope = enclosingScope;
    }

    @Override
    public String getScopeName() {
        return getName();
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    @Override
    public void define(Symbol sym) {
        symbols.put(sym.getName(), sym);
        orderedArgs.put(sym.getName(), sym);
    }

    @Override
    public Symbol resolve(String name) {
        Symbol symbol = symbols.get(name);
        if (symbol == null && enclosingScope != null) {
            return enclosingScope.resolve(name);
        }
        return symbol;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("[ordered Args: " + orderedArgs + "]");
        return builder.toString();
    }
}
