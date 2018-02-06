package com.hankerzheng.minesweep.cli.model;

import com.hankerzheng.minesweep.cli.exception.PositionUnreachableException;
import com.hankerzheng.minesweep.cli.exception.TooManyMinesException;
import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;

import java.util.Set;

import static com.hankerzheng.minesweep.cli.utils.MineGroundUtils.ITERATE_SEQ;

public class MineGround {

    public static final Integer UNSPOTTED = -1;
    public static final Integer BOOM = -2;
    public static final Integer MAKRED = -3;
    private final Integer height;
    private final Integer width;
    private final Integer mineCount;
    private final Integer[][] ground;
    private final Set<Integer> mines;

    public static MineGround createVisivleMineGround(final Integer height, final Integer width, final Integer mineCount) throws TooManyMinesException {
        return new MineGround(height, width, mineCount);
    }

    private MineGround(final Integer height, final Integer width, final Integer mineCount) throws TooManyMinesException {
        this.height = height;
        this.width = width;
        this.mineCount = mineCount;
        ground = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ground[i][j] = UNSPOTTED;
            }
        }
        mines = MineGroundUtils.generateMines(height, width, mineCount);
    }


    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getMineCount() {
        return mineCount;
    }

    public boolean isMine(final Integer row, final Integer col) {
        return mines.contains(row * getWidth() + col);
    }

    public Integer getPositionInformaiton(final Integer row, final Integer col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        return ground[row][col];
    }

    public boolean isVisitedPosition(final Integer row, final Integer col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        return ground[row][col] != UNSPOTTED;
    }

    public boolean isLegalPosition(final int row, final int col) {
        return isRowInRange(row) && isColInRange(col);
    }

    public Integer stepPosition(final Integer row, final Integer col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        if (isVisitedPosition(row, col)) {
            return ground[row][col];
        }
        if (isMine(row, col)) {
            ground[row][col] = BOOM;
            return BOOM;
        }
        ground[row][col] = getPositionEnvironment(row, col);
        return ground[row][col];
    }


    public Integer markPosition(final int row, final int col) throws PositionUnreachableException {
        positionPreCheck(row, col);
        if (ground[row][col] == UNSPOTTED) {
            ground[row][col] = MAKRED;
        }
        return ground[row][col];
    }


    private void positionPreCheck(final Integer row, final Integer col) throws PositionUnreachableException {
        if (!isLegalPosition(row, col)) {
            throw new PositionUnreachableException("The input Column # or Row # is out of range!!");
        }
    }

    private boolean isRowInRange(final Integer row) {
        return 0 <= row && row < getHeight();
    }

    private boolean isColInRange(final Integer col) {
        return 0 <= col && col < getWidth();
    }

    private Integer getPositionEnvironment(final Integer row, final Integer col) {
        int mineCount = 0;
        for (final Integer deltaRow : ITERATE_SEQ) {
            for (final Integer deltaCol : ITERATE_SEQ) {
                if (isMine(row + deltaRow, col + deltaCol)) {
                    mineCount++;
                }
            }
        }
        return mineCount;
    }


}
