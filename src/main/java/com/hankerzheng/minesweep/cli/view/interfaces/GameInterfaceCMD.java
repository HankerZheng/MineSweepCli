package com.hankerzheng.minesweep.cli.view.interfaces;

import com.hankerzheng.minesweep.cli.view.MineSweeperView;

public class GameInterfaceCMD implements UserInterface {

    public MineSweeperView mineSweeperView;

    private static final String CONTINUE_INFO = "";

    public GameInterfaceCMD(final MineSweeperView mineSweeperView) {
        this.mineSweeperView = mineSweeperView;
    }

    public void display() {
        printFirstCol();
        printGameDetail();
        System.out.println(CONTINUE_INFO);
    }

    private void printFirstCol() {
        System.out.print("|   |");
        for (int col = 0; col < mineSweeperView.getWidth(); col++) {
            System.out.format("C%-2d|", col);
        }
        System.out.println();
    }

    private void printGameDetail() {
        for (int row = 0; row < mineSweeperView.getHeight(); row++) {
            System.out.format("|R%-2d|", row);
            for (int col = 0; col < mineSweeperView.getWidth(); col++) {
                System.out.format(" %2s|", getSymbol(mineSweeperView.getPositionStatus(row, col)));
            }
            System.out.println();
        }
        System.out.println();
    }


    private String getSymbol(final Integer status) {
        if (status == MineSweeperView.UNSPOTTED) {
            return "[]";
        } else if (status == MineSweeperView.BOOMED) {
            return "**";
        } else if (status == MineSweeperView.MARKED) {
            return "MK";
        } else if (status == 0) {
            return "  ";
        }
        return String.format("%2d", status);
    }
}
