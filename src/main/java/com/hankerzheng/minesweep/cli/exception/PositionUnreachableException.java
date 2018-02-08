package com.hankerzheng.minesweep.cli.exception;

public class PositionUnreachableException extends Exception {

    public PositionUnreachableException(final int row, final int col) {
        super(String.format("The position [%d, %d] is out of range of current MineGround!", row, col));
    }
}
