package com.hankerzheng.minesweep.cli.model;

import com.hankerzheng.minesweep.cli.MineSweeperConfig;
import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;

import java.util.Set;

public class MineGround {

    private final Integer height;
    private final Integer width;
    private final Set<Integer> mines;

    public static MineGround createMineGround(final MineSweeperConfig mineSweeperConfig) {
        return new MineGround(mineSweeperConfig.getHeight(), mineSweeperConfig.getHeight(), mineSweeperConfig.getMineCount());
    }

    private MineGround(final int height, final int width, final int mineCount) {
        this.height = height;
        this.width = width;
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

    public boolean isMine(final int row, final int col) {
        return mines.contains(row * getWidth() + col);
    }

    public int getMinesAround(final int row, final int col) {
        int mineCount = 0;
        for (final int rowDel : MineGroundUtils.ITERATE_SEQ) {
            for (final int colDel : MineGroundUtils.ITERATE_SEQ) {
                if (isPositionInRange(row + rowDel, col + colDel)) {
                    mineCount += isMine(row + rowDel, col + colDel) ? 1 : 0;
                }
            }
        }
        return mineCount;
    }

    public boolean isPositionInRange(final int row, final int col) {
        return 0 <= row && row < height && 0 <= col && col < width;
    }


}
