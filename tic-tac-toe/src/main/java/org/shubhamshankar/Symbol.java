package org.shubhamshankar;

public enum Symbol {
    X("X"),
    O("O"),
    BLANK(" ");

    private final String displayName;
    Symbol(String displayName) {
        this.displayName = displayName;
    }

    public String toString() {
        return this.displayName;
    }
}
