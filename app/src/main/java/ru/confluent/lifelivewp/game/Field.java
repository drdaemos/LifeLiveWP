package ru.confluent.lifelivewp.game;

/**
 * Created by Евгений on 30.03.2015.
 */
public class Field {
    private int rows, cols;
    private Cell[][] cells;
    public Field(int rows, int cols, boolean populate){
        this.rows = rows;
        this.cols = cols;

        generate(populate);
    }

    public void generate(boolean random){
        cells = new Cell[rows][cols];
        for (int x = 0; x < rows; x++)
            for (int y = 0; y < cols; y++)
                cells[x][y] = new Cell(random);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return cols;
    }

    public Cell[][] getCells(){
        return cells;
    }

    public void updateCells() {
        Cell[][] nextGen = new Cell[rows][cols];
        boolean changed = false;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                nextGen[x][y] = getNextGenCell(x, y);
                if(!nextGen[x][y].equals(cells[x][y])){
                    changed = true;
                }
            }
        }
        if(!changed) {
            generate(true);
        } else {
            cells = nextGen;
        }
    }

    private Cell getNextGenCell(int x, int y) {
        Cell current = cells[x][y];
        Cell next = new Cell();
        int liveNeighbors = getNumLiveNeighbors(x, y);

        if (current.isAlive() && (liveNeighbors == 2 || liveNeighbors == 3))
            next.setDead(false);
        else if (current.isDead() && liveNeighbors == 3)
            next.setDead(false);

        return next;
    }

    private int getNumLiveNeighbors(int x, int y) {
        int living = 0;
        living += isAlive(x-1, y-1) ? 1 : 0;
        living += isAlive(x,   y-1) ? 1 : 0;
        living += isAlive(x+1, y-1) ? 1 : 0;
        living += isAlive(x-1, y)   ? 1 : 0;
        living += isAlive(x+1, y)   ? 1 : 0;
        living += isAlive(x-1, y+1) ? 1 : 0;
        living += isAlive(x,   y+1) ? 1 : 0;
        living += isAlive(x+1, y+1) ? 1 : 0;
        return living;
    }

    private boolean isAlive(int x, int y) {
        try {
            return cells[x][y].isAlive();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
