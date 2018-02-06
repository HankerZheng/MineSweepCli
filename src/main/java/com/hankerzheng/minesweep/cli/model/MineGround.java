package com.hankerzheng.minesweep.cli.model;

import com.hankerzheng.minesweep.cli.MineSweepConfig;
import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;

import java.util.Set;

public class MineGround {

    private final Integer[][] mineGround;
    private final Set<Integer> mines;

    private final Integer height;
    private final Integer width;

    public static MineGround createMineGround(final MineSweepConfig mineSweepConfig) {
        return new MineGround(mineSweepConfig.getHeight(), mineSweepConfig.getHeight(), mineSweepConfig.getMineCount());

    }

    private MineGround(final int height, final int width, final int mineCount) {
        this.height = height;
        this.width = width;
        mineGround = MineGroundUtils.initMineGround(height, width);
        mines = MineGroundUtils.generateMines(height, width, mineCount);

    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getMineCount() {
        return mines.size();
    }

    public Integer getPositionInfo(final int row, final int col) {
        return mineGround[row][col];
    }

    public boolean isMine(final int row, final int col) {
        return mines.contains(row * getWidth() + col);
    }

    public void updatePosition(final int row, final int col, final Integer status) {
        mineGround[row][col] = status;
    }
}
