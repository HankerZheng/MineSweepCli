package com.hankerzheng.minesweep.cli.exception;

public class TooManyMinesException extends Exception {
    public TooManyMinesException(final int maxMineCount, final int inputMineCount) {
        super(String.format("Only %d of mines are supported in this map, but %d mines are waited to be planted!", maxMineCount, inputMineCount));
    }
}
