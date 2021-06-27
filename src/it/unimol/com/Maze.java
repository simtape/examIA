package it.unimol.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    public Cell[][] mazeCells;

    public Cell startingPosition;
    public Cell endingPosition;

    public String start = "S";
    public String exit = "🏠";
    public String wall = "▀";
    public String path = " ";

    public Maze() {
        this.generateMaze();


    }

    public void generateMaze() {

        this.mazeCells = new Cell[11][11];

        List<String> elements = new ArrayList<>(List.of(this.wall, this.path, this.path, this.path));

        String cellValue;
        Random rand = new Random();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                if (i == 0 || i == 10) {
                    this.mazeCells[i][j] = new Cell(i, j);
                    this.mazeCells[i][j].setValue(this.wall);
                } else if (j == 0 || j == 10) {
                    this.mazeCells[i][j] = new Cell(i, j);
                    this.mazeCells[i][j].setValue(this.wall);
                } else {
                    cellValue = elements.get(rand.nextInt(elements.size()));
                    this.mazeCells[i][j] = new Cell(i, j);
                    this.mazeCells[i][j].setValue(cellValue);
                }

            }
        }

        this.mazeCells[0][1].setValue(this.start);
        this.startingPosition = this.mazeCells[0][1];

        this.mazeCells[9][10].setValue(this.exit);
        this.endingPosition = this.mazeCells[9][10];


    }

    public Cell getMazeCell(int i, int j) {
        return mazeCells[i][j];
    }

    public Cell getStartingPosition() {
        return startingPosition;
    }

    public Cell getEndingPosition() {
        return endingPosition;
    }

    public String getExitSymbol() {
        return exit;
    }

    public void cleanMaze() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                if (mazeCells[i][j].getValue().equals("#"))
                    mazeCells[i][j].setValue(" ");
            }
        }

    }

    public String getStartingSymbol() {
        return this.start;
    }

    public Cell[][] getMazeCells() {
        return mazeCells;
    }
}
