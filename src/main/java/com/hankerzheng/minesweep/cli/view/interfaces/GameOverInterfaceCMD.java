package com.hankerzheng.minesweep.cli.view.interfaces;

import com.hankerzheng.minesweep.cli.model.GameStatus;

public class GameOverInterfaceCMD implements UserInterface {

    private static final String WIN_INFO = "YOU WIN!!!";
    private static final String LOST_INFO = "YOU LOST!!!";
    private static final String COMMON_INFO =
            "Press RETURN to the WELCOME page...\n" +
                    "Press CTRL + C to end the program...\n";
    private GameStatus gameStatus;

    public GameOverInterfaceCMD(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void display() {
        if (gameStatus == GameStatus.WIN_GAME) {
            System.out.println(WIN_INFO);
        }
        if (gameStatus == gameStatus.LOSE_GAME) {
            System.out.println(LOST_INFO);
        }
        System.out.println(COMMON_INFO);
        System.console().readLine();
    }
}
