package com.hankerzheng.minesweep.cli.controller;

import com.hankerzheng.minesweep.cli.MineSweepConfig;
import com.hankerzheng.minesweep.cli.exception.PositionUnreachableException;
import com.hankerzheng.minesweep.cli.model.GameStatus;
import com.hankerzheng.minesweep.cli.model.MineGround;
import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;
import com.hankerzheng.minesweep.cli.view.MineSweepView;

public class MineSweepController {

    private MineGround mineGround;
    private MineSweepView mineSweepView;
    private MineSweepConfig mineSweepConfig;
    private int positionLeft;
    private boolean boom;

    public static MineSweepController createGameWithConfig(final MineSweepConfig mineSweepConfig) {
        return new MineSweepController(mineSweepConfig);
    }

    private MineSweepController(final MineSweepConfig mineSweepConfig) {
        this.mineSweepConfig = mineSweepConfig;
        mineGround = MineGround.createMineGround(mineSweepConfig);
        mineSweepView = MineSweepView.createMineView(mineSweepConfig.getHeight(), mineSweepConfig.getWidth(), mineGround);
        positionLeft = mineSweepConfig.getHeight() * mineSweepConfig.getWidth();
        boom = false;
    }

    public void markPosition(final int row, final int col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        mineSweepView.updateView(row, col, MineGroundUtils.MARKED);
    }

    public void checkPosition(final int row, final int col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        if (mineGround.getPositionInfo(row, col) != MineGroundUtils.UNSPOTTED) {
            return;
        }
        boom = boom || mineGround.isMine(row, col);
        int minesAround = getPositionEnironment(row, col);
        mineGround.updatePosition(row, col, minesAround);
        mineSweepView.updateView(row, col, minesAround);
        positionLeft--;
        if (!boom && minesAround == 0) {
            checkPositionsAround(row, col);
        }
    }

    public void aroundPosition(final int row, final int col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        checkPositionsAround(row, col);

    }

    private void checkPositionsAround(final int row, final int col) throws PositionUnreachableException {
        for (final int delRow : MineGroundUtils.ITERATE_SEQ) {
            for (final int delCol : MineGroundUtils.ITERATE_SEQ) {
                if (isPositionInRange(row + delRow, col + delCol)
                        && isPositionUnspottedInView(row + delRow, col + delCol)) {
                    checkPosition(row + delRow, col + delCol);
                }
            }

        }
    }

    private boolean isPositionUnspottedInView(final int row, final int col) {
        return mineSweepView.getMineGroundView()[row][col] == MineGroundUtils.UNSPOTTED;
    }

    public MineSweepView getMineSweepView() {
        return mineSweepView;
    }

    public GameStatus getGameStatus() {
        if (boom) {
            return GameStatus.LOSE_GAME;
        } else if (!boom && positionLeft == mineGround.getMineCount()) {
            return GameStatus.WIN_GAME;
        } else {
            return GameStatus.CONTINUE_GAME;
        }
    }


    private int getPositionEnironment(final int row, final int col) {
        if (mineGround.isMine(row, col)) {
            return MineGroundUtils.BOOMMED;
        }
        int mineAround = 0;
        for (final int delRow : MineGroundUtils.ITERATE_SEQ) {
            for (final int delCol : MineGroundUtils.ITERATE_SEQ) {
                if (isPositionInRange(row + delRow, col + delCol)
                        && mineGround.isMine(row + delRow, col + delCol)) {
                    mineAround += 1;
                }
            }
        }
        return mineAround;
    }

    private boolean isPositionInRange(final int row, final int col) {
        return 0 <= row && row < mineSweepConfig.getHeight()
                && 0 <= col && col < mineSweepConfig.getWidth();
    }

    private void positionPreCheck(final int row, final int col) throws PositionUnreachableException {
        if (!isPositionInRange(row, col)) {
            throw new PositionUnreachableException(String.format("The input position [%d, %d] is not in the map!", row, col));
        }
    }


}
