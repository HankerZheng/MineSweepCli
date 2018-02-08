package com.hankerzheng.minesweep.cli.utils;

import com.hankerzheng.minesweep.cli.MineSweeperConfig;
import com.hankerzheng.minesweep.cli.exception.IncorrectUserInputException;
import com.hankerzheng.minesweep.cli.model.GameMove;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IOUtils {

    public static String[] checkUserInput(final String userInput, final int expectedParamLength)
            throws IncorrectUserInputException {

        final String[] params = userInput.split("\\s+");
        if (params.length != expectedParamLength) {
            throw new IncorrectUserInputException(expectedParamLength, userInput);
        }
        return params;
    }


    public static MineSweeperConfig getConfigFromCMD() {
        boolean isSuccess = false;
        while (!isSuccess) {
            try {
                final List<Integer> params = Arrays.stream(checkUserInput(System.console().readLine(), 3))
                        .map(e -> Integer.parseInt(e)).collect(Collectors.toList());
                return MineSweeperConfig.createConfig(params.get(0), params.get(1), params.get(2));
            } catch (Exception e) {
                printErrorMessageToCMD(e.getMessage());
            }
        }
        return null;
    }


    public static void printErrorMessageToCMD(final String message) {
        System.console().printf("%s\n", message);
    }

    public static GameMove getGameMoveFromCMD() {
        boolean isSuccess = false;
        while (!isSuccess) {
            try {
                final String[] params = checkUserInput(System.console().readLine(), 3);
                return new GameMove(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            } catch (Exception e) {
                printErrorMessageToCMD(e.getMessage());
            }
        }
        return null;
    }
}
