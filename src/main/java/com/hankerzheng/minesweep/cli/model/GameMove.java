package com.hankerzheng.minesweep.cli.model;

public class GameMove {
    private GameOperation opreation;
    private int col;
    private int row;

    public GameMove(final String operation, final int row, final int col) {
        this.opreation = GameOperation.parseOperation(operation);
        this.col = col;
        this.row = row;
    }

    public GameOperation getOpreation() {
        return opreation;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}

