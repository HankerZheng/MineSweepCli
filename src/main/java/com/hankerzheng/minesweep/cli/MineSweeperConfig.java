package com.hankerzheng.minesweep.cli;

import com.hankerzheng.minesweep.cli.exception.IncorrectUserInputException;
import com.hankerzheng.minesweep.cli.exception.TooManyMinesException;

public class MineSweeperConfig {

    private int height;
    private int width;
    private int mineCount;

    public static MineSweeperConfig createConfig(final int height, final int width, final int mineCount) throws Exception {
        if (height <= 0 || width <= 0) {
            throw  new IncorrectUserInputException();
        }
        if (height * width < mineCount) {
            throw new TooManyMinesException(height * width, mineCount);
        }
        return new MineSweeperConfig(height, width, mineCount);
    }

    private MineSweeperConfig(final int height, final int width, final int mineCount) {
        this.height = height;
        this.width = width;
        this.mineCount = mineCount;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMineCount() {
        return mineCount;
    }
}
