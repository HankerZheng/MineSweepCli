package com.hankerzheng.minesweep.cli.utils;

import com.hankerzheng.minesweep.cli.exception.IncorrectUserInputException;

public class IOUtils {

    public static final String[] getUserInputFromCommandLine(final String userInput, final int expectedParamLength)
            throws IncorrectUserInputException {

        final String[] params = userInput.split("\\s+");
        if (params.length != expectedParamLength) {
            throw new IncorrectUserInputException(
                    String.format("Incorrect User Input!! %d parameters expected, but only %d was in [%s]",
                            expectedParamLength,
                            params.length,
                            userInput));
        }
        return params;
    }
}
