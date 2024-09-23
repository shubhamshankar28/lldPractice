package org.shubhamshankar;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("first-player", Symbol.O);
        Player p2 = new Player("second-player", Symbol.X);

        try {
            TicTacToeGame game = TicTacToeGame.initiateGame(p1, p2);
            game.playGame();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}