package com.hankerzheng.minesweep.cli.controller;

import com.hankerzheng.minesweep.cli.MineSweeperConfig;
import com.hankerzheng.minesweep.cli.exception.CannotCheckAroundPosition;
import com.hankerzheng.minesweep.cli.exception.PositionUnreachableException;
import com.hankerzheng.minesweep.cli.model.GameMove;
import com.hankerzheng.minesweep.cli.model.GameStatus;
import com.hankerzheng.minesweep.cli.model.MineGround;
import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;
import com.hankerzheng.minesweep.cli.view.MineSweeperView;


public class MineSweeperController {

    private MineGround mineGround;
    private MineSweeperView mineSweeperView;
    private GameStatus gameStatus;
    private int positionsLeftToCheck;


    public static MineSweeperController createMineSweeperGame(MineSweeperConfig config) {
        return new MineSweeperController(config);
    }

    public MineSweeperController(final MineSweeperConfig config) {
        mineGround = MineGround.createMineGround(config);
        mineSweeperView = MineSweeperView.createMineView(config.getHeight(), config.getWidth());
        gameStatus = GameStatus.CONTINUE_GAME;
        positionsLeftToCheck = 0;
    }

    public void makeMove(final GameMove move) throws Exception {
        switch (move.getOpreation()) {
            case MARK_OPERATION:
                userMarkPosition(move.getRow(), move.getCol());
                break;
            case CHECK_OPERATION:
                userCheckPosition(move.getRow(), move.getCol());
                break;
            case AROUND_OPERATION:
                userCheckAroundPosition(move.getRow(), move.getCol());
                break;
            case UNMARK_OPERATION:
                userUnmarkPosition(move.getRow(), move.getCol());
                break;
            default:
                break;
        }
    }

    private void userMarkPosition(final int row, final int col) throws Exception{
        preCheckPosition(row, col);
        mineSweeperView.updatePosition(row, col, MineSweeperView.MARKED);
    }

    private void userCheckPosition(final int row, final int col) throws Exception {
        preCheckPosition(row, col);
        checkPosition(row, col);
    }

    public void userCheckAroundPosition(final int row, final int col) throws Exception {
        preCheckPosition(row, col);
        if (mineSweeperView.getPositionStatus(row, col) == MineSweeperView.UNSPOTTED) {
            throw new CannotCheckAroundPosition("You should check the position first to do this `around` operation!");
        }
        checkPositionAround(row, col);
    }

    public void userUnmarkPosition(final int row, final int col) throws Exception {
        preCheckPosition(row, col);
        if (mineSweeperView.getPositionStatus(row, col) == MineSweeperView.MARKED) {
            mineSweeperView.updatePosition(row, col, MineSweeperView.UNSPOTTED);
        }
    }

    private void checkPosition(final int row, final int col) {
        if (mineSweeperView.getPositionStatus(row, col) != MineSweeperView.UNSPOTTED) {
            return;
        }

        if (mineGround.isMine(row, col)) {
            mineSweeperView.updatePosition(row, col, MineSweeperView.BOOMED);
            gameStatus = GameStatus.LOSE_GAME;
            return;
        }

        int minesAround = mineGround.getMinesAround(row, col);
        mineSweeperView.updatePosition(row, col, minesAround);
        positionsLeftToCheck--;
        if (positionsLeftToCheck == mineGround.getMineCount()) {
            gameStatus = GameStatus.WIN_GAME;
            return;
        }

        if (minesAround == 0) {
            checkPositionAround(row, col);
        }
    }

    private void checkPositionAround(final int row, final int col) {
        for (final int rowDel : MineGroundUtils.ITERATE_SEQ) {
            for (final int colDel : MineGroundUtils.ITERATE_SEQ) {
                if(mineGround.isPositionInRange(row + rowDel, col + colDel)) {
                    checkPosition(row + rowDel, col + colDel);
                }
            }
        }
    }


    private void preCheckPosition(final int row, final int col) throws Exception {
        if (!mineGround.isPositionInRange(row, col)) {
            throw new PositionUnreachableException(row, col);
        }
    }

    public MineSweeperView getMineSweeperView() {
        return mineSweeperView;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
