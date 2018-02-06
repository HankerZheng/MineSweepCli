package com.hankerzheng.minesweep.cli.view;

import com.hankerzheng.minesweep.cli.controller.MineSweep;
import com.hankerzheng.minesweep.cli.model.MineGround;

public class GameInterface implements UserInterface {

    public MineSweep mineSweep;

    private static final String CONTINUE_INFO = "";

    public GameInterface(final MineSweep mineSweep) {
        this.mineSweep = mineSweep;
    }


    public void display() {
        final Integer height = mineSweep.getViewData().length;
        final Integer width = mineSweep.getViewData()[0].length;
        System.out.print("|   |");
        for (int i = 0; i < width; i++) {
            System.out.format("%2dC|", i);
        }
        System.out.println();

        for (int i = 0; i < height; i++) {
            System.out.format("|%2dR|", i);
            for (int j = 0; j < width; j++) {
                System.out.format(" %2s|", getSymbol(mineSweep.getViewData()[i][j]));
            }
            System.out.println();
        }
        System.out.println(CONTINUE_INFO);
    }


    public String getSymbol(final Integer status) {
        if (status == MineGround.UNSPOTTED) {
            return "[]";
        } else if (status == MineGround.BOOM) {
            return "**";
        } else if (status == MineGround.MAKRED) {
            return "MK";
        } else if (status == 0) {
            return "  ";
        }
        return String.format("%2d", status);
    }
}
