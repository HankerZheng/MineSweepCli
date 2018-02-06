package com.hankerzheng.minesweep.cli.utils;

import java.util.HashSet;
import java.util.Set;

public class MineGroundUtils {
    public static final Integer[] ITERATE_SEQ = new Integer[]{-1, 0, 1};
    public static final Integer UNSPOTTED = -1;
    public static final Integer MARKED = -2;
    public static final Integer BOOMMED = -3;

    public static Integer[][] initMineGround(final int height, final int width) {
        final Integer[][] mineGround = new Integer[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                mineGround[row][col] = MineGroundUtils.UNSPOTTED;
            }
        }
        return mineGround;
    }

    public static Set<Integer> generateMines(final Integer height, final Integer width, final Integer mineCount) {
        int minesLeftToPlant = mineCount;
        int placesLeftToPlant = height * width;
        final Set<Integer> mineSet = new HashSet<Integer>();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                final double prob = (double) minesLeftToPlant / placesLeftToPlant;
                if (Math.random() < prob) {
                    mineSet.add(row * width + col);
                    minesLeftToPlant--;
                }
                placesLeftToPlant--;
            }
        }
        return mineSet;
    }

}
