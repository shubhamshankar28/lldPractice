package org.shubhamshankar;

public class PlayerUseSameSymbolException extends Exception {

    PlayerUseSameSymbolException() {
        super("Players cannot use the same exception");
    }
}
