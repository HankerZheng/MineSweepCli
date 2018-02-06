package com.hankerzheng.minesweep.cli;

import com.hankerzheng.minesweep.cli.controller.MineSweepController;
import com.hankerzheng.minesweep.cli.exception.IncorrectUserInputException;
import com.hankerzheng.minesweep.cli.model.GameStatus;
import com.hankerzheng.minesweep.cli.utils.IOUtils;
import com.hankerzheng.minesweep.cli.view.interfaces.GameInterface;
import com.hankerzheng.minesweep.cli.view.interfaces.GameOverInterface;
import com.hankerzheng.minesweep.cli.view.interfaces.UserInterface;
import com.hankerzheng.minesweep.cli.view.interfaces.WelcomeInterface;

public class MineSweepApp {

    private MineSweepConfig mineSweepConfig;
    private MineSweepController mineSweepController;
    private UserInterface currentInterface;


    public void initialize() {
        currentInterface = new WelcomeInterface();
        currentInterface.updateView();
        while (!readConfiguration()) {
            ;
        }
        mineSweepController = MineSweepController.createGameWithConfig(mineSweepConfig);
    }

    public void play() {
        currentInterface = new GameInterface(mineSweepController.getMineSweepView());
        currentInterface.updateView();

        while (mineSweepController.getGameStatus().equals(GameStatus.CONTINUE_GAME)) {
            while (!readPlayerMove()) {
                ;
            }
            currentInterface.updateView();
        }

        currentInterface = new GameOverInterface(mineSweepController.getGameStatus());
        currentInterface.appendView();
        System.console().readLine();
    }

    public void start() {
        for (int i = 0; i < 100; i++) {
            initialize();
            play();
        }
    }


    private boolean readConfiguration() {
        try {
            final String[] params = IOUtils.getUserInputFromCommandLine(System.console().readLine(), 3);
            mineSweepConfig = MineSweepConfig.getConfig(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Please try again!");
            return false;
        }
        return true;
    }

    private boolean readPlayerMove() {
        try {
            final String[] params = IOUtils.getUserInputFromCommandLine(System.console().readLine(), 3);
            if (params[0].equals("c") || params.equals("check")) {
                mineSweepController.checkPosition(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            } else if (params[0].equals("m") || params.equals("mark")) {
                mineSweepController.markPosition(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            } else if (params[0].equals("a") || params.equals("around")) {
                mineSweepController.aroundPosition(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            } else {
                throw new IncorrectUserInputException("Unsupport move detected!! Supported moves are 'c'/'check' and 'm'/'mark'");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Please try again!");
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        (new MineSweepApp()).start();
    }
}
