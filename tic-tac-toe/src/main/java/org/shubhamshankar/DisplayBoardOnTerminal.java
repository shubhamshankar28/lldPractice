package org.shubhamshankar;

import java.util.List;

public class DisplayBoardOnTerminal implements DisplayBoard {

    @Override
    public void displayBoard(List<List<Symbol>> grid) {
        int rows = grid.size();
        int columns = grid.get(0).size();
        for(int i=0; i<rows; ++i) {
            for(int j=0;j<columns; ++j) {
                System.out.print(grid.get(i).get(j) + "|");
            }
            System.out.println("");
            System.out.println("-------");
        }
    }
}
