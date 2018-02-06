package com.hankerzheng.minesweep.cli;

import com.hankerzheng.minesweep.cli.controller.MineSweep;
import com.hankerzheng.minesweep.cli.view.GameInterface;
import com.hankerzheng.minesweep.cli.view.GameOverInterface;
import com.hankerzheng.minesweep.cli.view.UserInterface;
import com.hankerzheng.minesweep.cli.view.WelcomeInterface;

import java.io.Console;
import java.util.HashMap;

public class MineSweepApp {

    private HashMap<String, UserInterface> interfaceFactory;
    private UserInterface currentInterface;
    private MineSweep mineSweep;
    private Console console;

    private static final String WELCOME_INTERFACE = "welcome";
    private static final String GAME_INTERFACE = "game";
    private static final String GAME_OVER_INTERFACE = "gameover";

    public static void start() {
        final MineSweepApp app = new MineSweepApp();
        app.initialize();
        for (int i = 0; i < 100; i++) {
            app.play();
        }
    }

    public void initialize() {
        console = System.console();
        interfaceFactory = new HashMap<>();
        interfaceFactory.put(WELCOME_INTERFACE, new WelcomeInterface());
    }

    public void play() {
        currentInterface = interfaceFactory.get(WELCOME_INTERFACE);
        currentInterface.updateView();
        while (!tryGetGameConfig()) { }

        currentInterface = interfaceFactory.get(GAME_INTERFACE);
        while (!mineSweep.isLost() && !mineSweep.isWin()) {
            currentInterface.updateView();
            while (!tryGetPlayInput()) { }
        }

        currentInterface.updateView();
        currentInterface = interfaceFactory.get(GAME_OVER_INTERFACE);
        currentInterface.appendView();
        tryGetPlayInputAfterGameIsOver();
    }

    private boolean tryGetGameConfig() {
        try {
            final String[] params = readUserInput(3);
            mineSweep = MineSweep.createNewMineSweep(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            interfaceFactory.put(GAME_INTERFACE, new GameInterface(mineSweep));
            interfaceFactory.put(GAME_OVER_INTERFACE, new GameOverInterface(mineSweep));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean tryGetPlayInput() {
        try {
            final String[] params = readUserInput(3);
            if (params[0].equals("c")) {
                mineSweep.clickPosition(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            } else if (params[0].equals("m")) {
                mineSweep.markPosition(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            } else {
                throw new IllegalArgumentException("Unsupport operation! Only support 'c' for check and 'm' for mark!");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void tryGetPlayInputAfterGameIsOver() {
        console.readLine();
    }


    private String[] readUserInput(final int expectedLength) throws Exception {
        final String userInput = console.readLine();
        final String[] params = userInput.split("/w+");
        if (params.length != expectedLength) {
            throw new IllegalArgumentException("Error input parameter");
        }
        return params;
    }
}
