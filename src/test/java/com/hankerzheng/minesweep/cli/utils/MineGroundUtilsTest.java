package com.hankerzheng.minesweep.cli.utils;

import org.junit.Test;

import java.util.Set;

public class MineGroundUtilsTest {

    @Test
    public void checkRandomGenerate() throws Exception {
        final Set<Integer> mines = MineGroundUtils.generateMines(8, 8, 10);
        printMines(mines, 8, 8);
    }

    private void printMines(final Set<Integer> mines, final int height, final int width) {
        for(final Integer mine: mines) {
            System.out.format("row: %d, col: %d\n", mine / width, mine % width);
        }
    }
}