package com.hankerzheng.minesweep.cli;

import com.hankerzheng.minesweep.cli.controller.MineSweeperController;
import com.hankerzheng.minesweep.cli.model.GameMove;
import com.hankerzheng.minesweep.cli.model.GameStatus;
import com.hankerzheng.minesweep.cli.utils.IOUtils;
import com.hankerzheng.minesweep.cli.view.interfaces.GameInterfaceCMD;
import com.hankerzheng.minesweep.cli.view.interfaces.GameOverInterfaceCMD;
import com.hankerzheng.minesweep.cli.view.interfaces.UserInterface;
import com.hankerzheng.minesweep.cli.view.interfaces.WelcomeInterfaceCMD;

public class MineSweeperApp {

    private MineSweeperController mineSweeperController;
    private UserInterface currentInterface;

    public void start() {
        initialize();
        for(int i = 0; i < 100; i++) {
            play();
        }
    }

    public void initialize() {
    }

    public void play() {
        currentInterface = new WelcomeInterfaceCMD();
        currentInterface.updateView();
        final MineSweeperConfig config = IOUtils.getConfigFromCMD();
        mineSweeperController = MineSweeperController.createMineSweeperGame(config);

        currentInterface = new GameInterfaceCMD(mineSweeperController.getMineSweeperView());
        currentInterface.updateView();
        while (mineSweeperController.getGameStatus() == GameStatus.CONTINUE_GAME) {
            try {
                final GameMove thisMove = IOUtils.getGameMoveFromCMD();
                mineSweeperController.makeMove(thisMove);
                currentInterface.updateView();
            } catch (Exception e) {
                IOUtils.printErrorMessageToCMD(e.getMessage());
            }
        }

        currentInterface = new GameOverInterfaceCMD(mineSweeperController.getGameStatus());
        currentInterface.appendView();
    }


    public static void main(String[] args) {
        final MineSweeperApp app = new MineSweeperApp();
        app.start();
    }

}
