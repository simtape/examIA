package it.unimol.com;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Handler {
    Maze mazeDFS;
    Maze mazeBFS;
    Maze mazeNotSolved;
    String bfsPath = "";
    boolean exit = false;


    public void newGame() {

        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {

            System.out.println("");
            System.out.println("\t\t\t\t\t\t\t\t\t\t  ______  _____  _____          _____  ______   _______ _    _ ______   __  __           ____________ ");
            System.out.println("\t\t\t\t\t\t\t\t\t\t |  ____|/ ____|/ ____|   /\\   |  __ \\|  ____| |__   __| |  | |  ____| |  \\/  |   /\\    |___  /  ____|");
            System.out.println("\t\t\t\t\t\t\t\t\t\t | |__  | (___ | |       /  \\  | |__) | |__       | |  | |__| | |__    | \\  / |  /  \\      / /| |__   ");
            System.out.println("\t\t\t\t\t\t\t\t\t\t |  __|  \\___ \\| |      / /\\ \\ |  ___/|  __|      | |  |  __  |  __|   | |\\/| | / /\\ \\    / / |  __|  ");
            System.out.println("\t\t\t\t\t\t\t\t\t\t | |____ ____) | |____ / ____ \\| |    | |____     | |  | |  | | |____  | |  | |/ ____ \\  / /__| |____ ");
            System.out.println("\t\t\t\t\t\t\t\t\t\t |______|_____/ \\_____/_/    \\_\\_|    |______|    |_|  |_|  |_|______| |_|  |_/_/    \\_\\/_____|______|\n\n");

            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t[1] -> NEW GAME <- [1]");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  [0] -> EXIT <- [0]\n");

            scelta = scanner.nextInt();

            switch (scelta) {

                case 1:

                    this.createMaze();
                    this.showMaze();
                    break;


                case 0:
                    System.out.println("ARRIVEDERCI\n");
                    break;

                default:
                    System.out.println("[SCELTA NON VALIDA]\n");
                    break;

            }


        } while (scelta != 0 && scelta != 1);
    }

    public void showMaze() {

        //calcolo del percorso con dfs e stampa
        this.dfs(this.mazeDFS);
        this.bfs(this.mazeBFS);

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    [DFS SOLUTION]\t\t\t\t\t\t[BFS SOLUTION]\n");
        for (int i = 0; i < 11; i++) {

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");

            for (int j = 0; j < 11; j++) {
                System.out.print(this.mazeDFS.getMazeCell(i, j).getValue() + " ");
            }

            System.out.print("\t\t\t\t");


            for (int j = 0; j < 11; j++) {
                System.out.print(this.mazeBFS.getMazeCell(i, j).getValue() + " ");
            }

            System.out.println(" ");

        }

        System.out.println("");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tBEST PATH FOUND BY BFS:");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + bfsPath);

    }

    public void dfs(Maze maze) {

        Stack<Cell> stack = new Stack<>();
        stack.push(maze.getStartingPosition());

        Cell currentCell;
        Cell adjacentCell;

        while (!stack.empty()) {

            currentCell = stack.pop();
            if (isExit(currentCell)) {
                exit = true;
                break;
            }

            markAsVisited(currentCell);

            //north
            if (isInMaze(currentCell.getX() - 1, currentCell.getY())) {
                adjacentCell = moveUp(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    stack.push(adjacentCell);

                }
            }

            //south
            if (isInMaze(currentCell.getX() + 1, currentCell.getY())) {
                adjacentCell = moveDown(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    stack.push(adjacentCell);

                }
            }

            //east
            if (isInMaze(currentCell.getX(), currentCell.getY() + 1)) {
                adjacentCell = moveRight(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    stack.push(adjacentCell);

                }
            }

            //west
            if (isInMaze(currentCell.getX(), currentCell.getY() - 1)) {
                adjacentCell = moveLeft(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    stack.push(adjacentCell);


                }
            }

        }
        maze.getMazeCell(0,1).setValue("S");



    }

    public void bfs(Maze maze) {

        LinkedList<Cell> queue = new LinkedList<>();
        LinkedList<String> allPaths = new LinkedList<>();

        allPaths.add(bfsPath);
        Cell currentCell;
        Cell adjacentCell;

        queue.add(maze.getStartingPosition());

        while (!queue.isEmpty()) {
            bfsPath = allPaths.poll();
            currentCell = queue.poll();
            if (isExit(currentCell))
                break;

            markAsVisited(currentCell);

            //goes north
            if (isInMaze(currentCell.getX() - 1, currentCell.getY())) {
                adjacentCell = moveUp(currentCell, maze);

                if (isWalkable(adjacentCell)) {
                    queue.add(adjacentCell);
                    String newPath = bfsPath + "N";
                    allPaths.add(newPath);
                }
            }

            //goes south
            if (isInMaze(currentCell.getX() + 1, currentCell.getY())) {
                adjacentCell = moveDown(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    queue.add(adjacentCell);
                    String newPath = bfsPath + "S";
                    allPaths.add(newPath);
                }
            }

            //goes east
            if (isInMaze(currentCell.getX(), currentCell.getY() + 1)) {
                adjacentCell = moveRight(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    queue.add(adjacentCell);
                    String newPath = bfsPath + "E";
                    allPaths.add(newPath);
                }
            }

            //goes west
            if (isInMaze(currentCell.getX(), currentCell.getY() - 1)) {
                adjacentCell = moveLeft(currentCell, maze);
                if (isWalkable(adjacentCell)) {
                    queue.add(adjacentCell);
                    String newPath = bfsPath + "W";
                    allPaths.add(newPath);
                }
            }

        }

        if (!queue.isEmpty()) {


        }
        reconstructPath(maze, bfsPath);

    }

    public Cell moveUp(Cell currentCell, Maze maze) {
        return maze.mazeCells[currentCell.getX() - 1][currentCell.getY()];
    }

    public Cell moveDown(Cell currentCell, Maze maze) {
        return maze.mazeCells[currentCell.getX() + 1][currentCell.getY()];
    }

    public Cell moveRight(Cell currentCell, Maze maze) {
        return maze.mazeCells[currentCell.getX()][currentCell.getY() + 1];
    }

    public Cell moveLeft(Cell currentCell, Maze maze) {
        return maze.mazeCells[currentCell.getX()][currentCell.getY() - 1];
    }

    public void markAsVisited(Cell cell) {
        cell.setValue("#");
    }

    public boolean isInMaze(int x, int y) {

        if (x >= 0 && x < 11 && y >= 0 && y < 11) {
            return true;
        } else return false;

    }

    public boolean isWalkable(Cell cell) {

        if (cell.getValue().equals("▀") || cell.getValue().equals("#")) {
            return false;
        } else return true;

    }

    public boolean isExit(Cell cell) {
        if (cell.getX() == mazeDFS.getEndingPosition().getX() && cell.getY() == mazeDFS.getEndingPosition().getY()) {
            return true;
        } else return false;
    }

    public Maze cloneMaze(Maze maze) {
        Maze newMaze = new Maze();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                newMaze.mazeCells[i][j].setValue(maze.getMazeCell(i, j).getValue());
            }
        }
        return newMaze;
    }

    public void reconstructPath(Maze maze, String path) {
        int x = 0;
        int y = 1;

        for (int i = 0; i < path.length(); i++) {
            char character = path.charAt(i);

            switch (character) {
                case 'N':
                    x--;
                    break;
                case 'S':
                    x++;
                    break;
                case 'W':
                    y--;
                    break;
                case 'E':
                    y++;
                    break;
            }
            maze.getMazeCell(x, y).setValue("+");

        }
        maze.getMazeCell(0, 1).setValue(maze.getStartingSymbol());
        maze.getMazeCell(9, 10).setValue(maze.getExitSymbol());

    }

    public void createMaze() {
        do {
            mazeDFS = new Maze();
            dfs(mazeDFS);
        } while (!exit);
        mazeDFS.cleanMaze();
        mazeBFS = cloneMaze(mazeDFS);
        mazeNotSolved = cloneMaze(mazeDFS);
    }

    public Maze getMazeDFS() {
        return mazeDFS;
    }

    public Maze getMazeBFS() {
        return mazeBFS;
    }

    public Maze getMazeNotSolved() {
        return mazeNotSolved;
    }
}
