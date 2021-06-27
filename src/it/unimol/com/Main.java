package it.unimol.com;

import it.unimol.com.gui.Board;

public class Main {
    public static void main (String args[]){
        Handler handler = new Handler();

        //handler.newGame();
        handler.createMaze();
        handler.showMaze();

        Board board = new Board(handler.getMazeBFS(), handler.getMazeDFS(), handler.getMazeNotSolved());
        board.setSize(600, 600);
        board.setVisible(true);

    }
}
