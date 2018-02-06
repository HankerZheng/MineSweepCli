package com.hankerzheng.minesweep.cli.controller;

import com.hankerzheng.minesweep.cli.exception.PositionUnreachableException;
import com.hankerzheng.minesweep.cli.exception.TooManyMinesException;
import com.hankerzheng.minesweep.cli.model.MineGround;
import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;

public class MineSweep {

    private MineGround mineGround;
    private Integer[][] viewData;
    private int unknownCount;
    private boolean boom;

    public static MineSweep createNewMineSweep(final Integer height, final Integer width, final Integer mineCount) throws TooManyMinesException {
        return new MineSweep(height, width, mineCount);
    }

    private MineSweep(final Integer height, final Integer width, final Integer mineCount) throws TooManyMinesException {
        unknownCount = height * width;
        boom = false;
        mineGround = MineGround.createVisivleMineGround(height, width, mineCount);
        viewData = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                viewData[i][j] = MineGround.UNSPOTTED;
            }
        }
    }

    public void clickPosition(final Integer row, final Integer col) throws PositionUnreachableException {
        if (mineGround.isVisitedPosition(row, col)) {
            return;
        }
        boom = boom || mineGround.isMine(row, col);
        viewData[row][col] = mineGround.stepPosition(row, col);
        unknownCount--;
        if (!isLost() && mineGround.getPositionInformaiton(row, col) == 0) {
            for (final Integer deltaRow : MineGroundUtils.ITERATE_SEQ) {
                for (final Integer deltaCol : MineGroundUtils.ITERATE_SEQ) {
                    if (mineGround.isLegalPosition(row + deltaRow, col + deltaCol))
                        clickPosition(row + deltaRow, col + deltaCol);
                }
            }
        }
    }

    public Integer[][] getViewData() {
        return viewData;
    }

    public boolean isLost() {
        return boom;
    }

    public boolean isWin() {
        return boom == false && unknownCount == mineGround.getMineCount();
    }

    public void markPosition(final int row, final int col) throws PositionUnreachableException {
        if (mineGround.isVisitedPosition(row, col)) {
            return;
        }
        viewData[row][col] = mineGround.markPosition(row, col);
    }
}
