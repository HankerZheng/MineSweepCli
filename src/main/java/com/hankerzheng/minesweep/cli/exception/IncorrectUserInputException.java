package com.hankerzheng.minesweep.cli.exception;

public class IncorrectUserInputException extends Exception {

    public IncorrectUserInputException(final Integer expectedLength, final String actualInput) {
        super(String.format("Error userInput!! %d parameters expected. The number doesn't match with input [%s]", expectedLength, actualInput));
    }

    public IncorrectUserInputException() {
        super("Width or Height of the mine ground should be larger than zero!");
    }
}
