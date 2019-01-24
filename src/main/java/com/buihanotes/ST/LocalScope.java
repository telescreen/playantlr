package com.buihanotes.ST;

public class LocalScope extends BaseScope {
    public LocalScope(Scope enclosingScope) {
        super("local", enclosingScope);
    }
}
