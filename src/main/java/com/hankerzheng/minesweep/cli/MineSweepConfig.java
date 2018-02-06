package com.hankerzheng.minesweep.cli;

import com.hankerzheng.minesweep.cli.exception.IncorrectUserInputException;
import com.hankerzheng.minesweep.cli.exception.TooManyMinesException;

public class MineSweepConfig {
    int height;
    int width;
    int mineCount;

    static MineSweepConfig getDefaultConfig() {
        return new MineSweepConfig(8, 8, 10);
    }

    static MineSweepConfig getConfig(final int height, final int width, final int mineCount) throws Exception {
        if (height <= 0 || width <= 0) {
            throw new IncorrectUserInputException("The height and width of the mine ground should larger than zero!");
        }
        if (height * width < mineCount) {
            throw new TooManyMinesException("Too many mines to plant, the mineGround is not big enough!!");
        }
        System.out.format("Config Created %d, %d, %d", height, width, mineCount);
        final MineSweepConfig config = new MineSweepConfig(height, width, mineCount);
        return config;
    }

    private MineSweepConfig(final int height, final int width, final int mineCount) {
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
