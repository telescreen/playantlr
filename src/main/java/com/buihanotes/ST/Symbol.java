package com.buihanotes.ST;

public class Symbol {
    private String name;
    private Type type;

    public Symbol(String name) {
        this.name = name;
    }

    public Symbol(String name, Type type) {
        this(name);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        if (type != null) {
            return "<" + getName() + ":" + type + ">";
        }
        return getName();
    }
}
