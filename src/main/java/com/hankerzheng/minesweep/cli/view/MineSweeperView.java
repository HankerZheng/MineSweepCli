package com.hankerzheng.minesweep.cli.view;

import org.springframework.stereotype.Component;

@Component
public class MineSweeperView {

    public static final int UNSPOTTED = -1;
    public static final int MARKED = -2;
    public static final int BOOMED = -3;

    private int[][] mineGroundView;


    public static MineSweeperView createMineView(final int height, final int width) {
        final MineSweeperView view = new MineSweeperView(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                view.updatePosition(i, j, UNSPOTTED);
            }
        }
        return view;
    }

    public int getHeight() {
        return mineGroundView.length;
    }

    public int getWidth() {
        return mineGroundView[0].length;
    }

    private MineSweeperView(final int height, final int width) {
        this.mineGroundView = new int[height][width];
    }

    public void updatePosition(final int row, final int col, final int status) {
        mineGroundView[row][col] = status;
    }

    public int getPositionStatus(final int row, final int col) {
        return mineGroundView[row][col];
    }
}
