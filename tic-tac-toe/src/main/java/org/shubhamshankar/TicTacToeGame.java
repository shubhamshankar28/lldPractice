package org.shubhamshankar;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame {

    DisplayBoard output;
    UserInput fetchInput;
    List<List<Symbol>> symbols;
    private Player currentPlayer;
    private Player p1;
    private Player p2;
    private TicTacToeGame() {}
    private TicTacToeGame(Player p1, Player p2) {
        currentPlayer = p1;
        this.p1 = p1;
        this.p2 = p2;
        symbols = new ArrayList<>();

        for(int i=0;i<3;++i) {
            List<Symbol> temp =  new ArrayList<>();
            for(int j=0;j<3;++j) {
                temp.add(Symbol.BLANK);
            }
            symbols.add(temp);
        }

        fetchInput = new UserInputFromTerminal();
        output = new DisplayBoardOnTerminal();
    }
    private static TicTacToeGame ticTacToe = null;


    public static TicTacToeGame initiateGame(Player p1, Player p2) throws PlayerUseSameSymbolException {
        if(ticTacToe != null) {
            return ticTacToe;
        }

        if(p1.getSymbol().equals(p2.getSymbol())){
            throw new PlayerUseSameSymbolException();
        }
        ticTacToe = new TicTacToeGame(p1, p2);

        return ticTacToe;
    }

    public void playGame() {

        State finalState;
        while(true) {
            System.out.println("Player : " + currentPlayer.getName() + " has to play");

            output.displayBoard(symbols);
            InputCoordinate inputCoodinates = retrieveInputCoordinate();
            symbols.get(inputCoodinates.getX()).set(inputCoodinates.getY(), currentPlayer.getSymbol());
            State currentState = GameValidator.determineGameState(symbols);
            if(!currentState.equals(State.IN_PROGRESS)) {
                finalState = currentState;
                break;
            }

            if(currentPlayer.equals(p1)) {
                currentPlayer = p2;
            }
            else {
                currentPlayer = p1;
            }
        }

        System.out.println("Game has ended");
        output.displayBoard(symbols);
        if(finalState.equals(State.DRAW)) {
            System.out.println("It is a draw");
        }
        else {
            System.out.println(currentPlayer.getName() + " has won!");
        }
    }

    InputCoordinate retrieveInputCoordinate() {
        InputCoordinate finalCoordinate;
        while(true) {
            InputCoordinate coords = fetchInput.collectInputFromUser();
            if(GameValidator.isValidMove(coords, this.symbols)) {
               finalCoordinate = coords;
               break;
            }

            System.out.println("Entered move is invalid");
        }

        return finalCoordinate;
    }

}
