package com.hankerzheng.minesweep.cli.view;

import com.hankerzheng.minesweep.cli.model.MineGround;
import org.springframework.stereotype.Component;

@Component
public class MineSweepView {

    private Integer[][] mineGroundView;

    public static MineSweepView createMineView(final int height, final int width, final MineGround mineGround) {
        final Integer[][] view = new Integer[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                view[row][col] = mineGround.getPositionInfo(row, col);
            }
        }
        return new MineSweepView(view);
    }

    public MineSweepView(final Integer[][] mineGroundView) {
        this.mineGroundView = mineGroundView;
    }

    public void updateView(final int row, final int col, final Integer status) {
        mineGroundView[row][col] = status;
    }

    public Integer getHeight() {
        return mineGroundView.length;
    }

    public Integer getWidth() {
        return mineGroundView[0].length;
    }

    public Integer[][] getMineGroundView() {
        return mineGroundView;
    }
}
