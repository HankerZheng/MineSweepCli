package com.hankerzheng.minesweep.cli.view.interfaces;

import com.hankerzheng.minesweep.cli.utils.MineGroundUtils;
import com.hankerzheng.minesweep.cli.view.MineSweepView;

public class GameInterface implements UserInterface {

    public MineSweepView mineSweepView;

    private static final String CONTINUE_INFO = "";

    public GameInterface(final MineSweepView mineSweepView) {
        this.mineSweepView = mineSweepView;
    }

    public void display() {
        displayFirstCol();
        displayGameDetail();
        System.out.println(CONTINUE_INFO);
    }

    private void displayFirstCol() {
        System.out.print("|   |");
        for (int col = 0; col < mineSweepView.getWidth(); col++) {
            System.out.format("C%-2d|", col);
        }
        System.out.println();
    }

    private void displayGameDetail() {
        for (int row = 0; row < mineSweepView.getHeight(); row++) {
            System.out.format("|R%-2d|", row);
            for (int col = 0; col < mineSweepView.getWidth(); col++) {
                System.out.format(" %2s|", getSymbol(mineSweepView.getMineGroundView()[row][col]));
            }
            System.out.println();
        }
        System.out.println();
    }


    private String getSymbol(final Integer status) {
        if (status == MineGroundUtils.UNSPOTTED) {
            return "[]";
        } else if (status == MineGroundUtils.BOOMMED) {
            return "**";
        } else if (status == MineGroundUtils.MARKED) {
            return "MK";
        } else if (status == 0) {
            return "  ";
        }
        return String.format("%2d", status);
    }
}
