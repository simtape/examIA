package it.unimol.com;

public class Cell {
    String value;

    int x;
    int y;
    int h = 0;
    int f = 0;
    int g = 0;

    public Cell(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
