package com.hankerzheng.minesweep.cli.view;

import com.hankerzheng.minesweep.cli.controller.MineSweep;

public class GameOverInterface implements UserInterface {

    private static final String WIN_INFO = "YOU WIN!!!";
    private static final String LOST_INFO = "YOU LOST!!!";
    private static final String COMMON_INFO =
            "Press RETURN to the WELCOME page...\n" +
                    "Press CTRL + C to end the program...\n";


    private final MineSweep mineSweep;

    public GameOverInterface(final MineSweep mineSweep) {
        this.mineSweep = mineSweep;
    }

    public void display() {
        if (mineSweep.isWin()) {
            System.out.println(WIN_INFO);
        } else if (mineSweep.isLost()) {
            System.out.println(LOST_INFO);
        }
        System.out.println();;
        System.out.println(COMMON_INFO);
    }
}
