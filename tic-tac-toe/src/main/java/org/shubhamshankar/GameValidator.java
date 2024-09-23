package org.shubhamshankar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameValidator {
    public static boolean isValidMove(InputCoordinate inp, List<List<Symbol>> grid) {

        if((inp.getX() < 0) || (inp.getX() > 2)) {
            return false;
        }

        if((inp.getY() < 0) || (inp.getY() > 2)) {
            return false;
        }

        int x = inp.getX();
        int y = inp.getY();
        if(grid.get(x).get(y).equals(Symbol.BLANK))
            return true;

        return false;
    }

    public static State determineGameState(List<List<Symbol>> grid) {

        int blankEntries = 0;
        for(int i=0; i<3; ++i) {

            Map<Symbol, Integer> symbolCount = new HashMap<>();
            Map<Symbol, Integer> colSymbolCount = new HashMap<>();
            for(int j=0; j<3; ++j) {
                Symbol currentSymbol = grid.get(i).get(j);
                Symbol alternateCurrentSymbol = grid.get(j).get(i);
                if(currentSymbol.equals(Symbol.BLANK))
                    blankEntries++;




                int currentCount = symbolCount.getOrDefault(currentSymbol, 0);
                symbolCount.put(currentSymbol, currentCount + 1);

                int colCurrentCount = colSymbolCount.getOrDefault(alternateCurrentSymbol, 0);
                colSymbolCount.put(alternateCurrentSymbol , colCurrentCount + 1);
            }

            if(symbolCount.getOrDefault(Symbol.O, 0) == 3){
                return State.WIN;
            }

            if(colSymbolCount.getOrDefault(Symbol.O, 0) == 3){
                return State.WIN;
            }

            if(symbolCount.getOrDefault(Symbol.X , 0) == 3){
                return State.WIN;
            }

            if(colSymbolCount.getOrDefault(Symbol.X, 0) == 3){
                return State.WIN;
            }
        }

        if(blankEntries == 0) {
            return State.DRAW;
        }

        Map<Symbol, Integer> symbolCount = new HashMap<>();
        Map<Symbol, Integer> alternateSymbolCount = new HashMap<>();
        for(int i=0; i<3; ++i) {
            Symbol currentSymbol = grid.get(i).get(i);
            Symbol alternateCurrentSymbol = grid.get(i).get(2-i);

            int currentCount = symbolCount.getOrDefault(currentSymbol, 0);
            symbolCount.put(currentSymbol, currentCount + 1);

            int colCurrentCount = alternateSymbolCount.getOrDefault(alternateCurrentSymbol, 0);
            alternateSymbolCount.put(alternateCurrentSymbol , colCurrentCount + 1);

        }

        if(symbolCount.getOrDefault(Symbol.O, 0) == 3){
            return State.WIN;
        }

        if(alternateSymbolCount.getOrDefault(Symbol.O, 0) == 3){
            return State.WIN;
        }

        if(symbolCount.getOrDefault(Symbol.X, 0) == 3){
            return State.WIN;
        }

        if(alternateSymbolCount.getOrDefault(Symbol.X, 0) == 3){
            return State.WIN;
        }


        return State.IN_PROGRESS;
    }
}
