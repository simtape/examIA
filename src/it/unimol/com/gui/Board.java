package it.unimol.com.gui;

import it.unimol.com.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Board extends JFrame {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] mazeBoardSquares = new JButton[11][11];
    private JPanel mazeBoard;
    private JPanel dfsMazeBoard;
    private JPanel bfsMazeBoard;
    private JButton bfs = new JButton("Bfs");
    private JButton dfs = new JButton("Dfs");
    private Maze bfsMaze;
    private Maze dfsMaze;
    private Maze mazeNotSolved;

    public Board(Maze bfsMaze, Maze dfsMaze, Maze mazeNotSolved) throws HeadlessException {
        super();
        this.bfsMaze = bfsMaze;
        this.dfsMaze = dfsMaze;
        this.mazeNotSolved = mazeNotSolved;

        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(bfs);
        tools.add(dfs);
        tools.addSeparator();
        mazeBoard = new JPanel(new GridLayout(0, 11));
        mazeBoard.setBorder(new LineBorder(Color.BLACK));


        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);

                if (this.mazeNotSolved.getMazeCell(i, j).getValue().equals("S") ||
                        this.mazeNotSolved.getMazeCell(i, j).getValue().equals("🏠")) {
                    b.setBackground(Color.GREEN);

                } else if (this.mazeNotSolved.getMazeCell(i, j).getValue().equals("▀"))
                    b.setBackground(Color.BLACK);

                else
                    b.setBackground(Color.white);
                mazeBoardSquares[i][j] = b;
                mazeBoard.add(mazeBoardSquares[i][j]);
            }
        }
        gui.add(mazeBoard);

        generateBfsPanel();
        generateDfsPanel();

        bfsMazeBoard.setVisible(false);
        dfsMazeBoard.setVisible(false);


        dfsListener();
        bfsListener();
        this.add(gui);
    }

    private void dfsListener() {
        this.dfs.addActionListener(event -> {
            mazeBoard.setVisible(false);
            bfsMazeBoard.setVisible(false);
            dfsMazeBoard.setVisible(true);
            gui.remove(bfsMazeBoard);
            gui.add(dfsMazeBoard);
        });


    }

    private void bfsListener() {
        this.bfs.addActionListener(event -> {
            mazeBoard.setVisible(false);
            bfsMazeBoard.setVisible(true);
            dfsMazeBoard.setVisible(false);
            gui.remove(dfsMazeBoard);
            gui.add(bfsMazeBoard);

        });


    }

    private void generateBfsPanel() {
        bfsMazeBoard = new JPanel(new GridLayout(0, 11));
        bfsMazeBoard.setBorder(new LineBorder(Color.BLACK));

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);

                if (    this.bfsMaze.getMazeCell(i, j).getValue().equals("S") ||
                        this.bfsMaze.getMazeCell(i, j).getValue().equals("🏠") ||
                        this.bfsMaze.getMazeCell(i, j).getValue().equals("+")
                ) {
                    b.setBackground(Color.GREEN);

                } else if (bfsMaze.getMazeCell(i, j).getValue().equals("#")) {
                    b.setBackground(Color.YELLOW);

                } else if (bfsMaze.getMazeCell(i, j).getValue().equals("▀"))
                    b.setBackground(Color.BLACK);

                else
                    b.setBackground(Color.white);
                mazeBoardSquares[i][j] = b;
                bfsMazeBoard.add(mazeBoardSquares[i][j]);
            }
        }

    }

    private void generateDfsPanel() {
        dfsMazeBoard = new JPanel(new GridLayout(0, 11));
        dfsMazeBoard.setBorder(new LineBorder(Color.BLACK));

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                if (this.dfsMaze.getMazeCell(i, j).getValue().equals("S") ||
                        this.dfsMaze.getMazeCell(i, j).getValue().equals("🏠")) {
                    b.setBackground(Color.GREEN);

                } else if (dfsMaze.getMazeCell(i, j).getValue().equals("#")) {
                    b.setBackground(Color.YELLOW);

                } else if (dfsMaze.getMazeCell(i, j).getValue().equals("+")) {
                    b.setBackground(Color.GREEN);

                } else if (dfsMaze.getMazeCell(i, j).getValue().equals("▀"))
                    b.setBackground(Color.BLACK);

                else
                    b.setBackground(Color.white);
                mazeBoardSquares[i][j] = b;
                dfsMazeBoard.add(mazeBoardSquares[i][j]);
            }
        }
    }
}
