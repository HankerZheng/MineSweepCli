package com.hankerzheng.minesweep.cli.utils;

import com.hankerzheng.minesweep.cli.exception.IncorrectUserInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IOUtilsTest {

    @Test
    public void shouldCorrectParseUserInput() throws Exception {
        verify("8 8 8", 3, "8", "8", "8");
        verify("8 \t 8     8", 3, "8", "8", "8");
        verify("r 8 8", 3, "r", "8", "8");
        verify("r \t 8 \n 8", 3, "r", "8" ,"8");
        verify("8 8 60", 3, "8", "8" ,"60");
    }


    @Test(expected = IncorrectUserInputException.class)
    public void shouldThrowExceptionIfInputIncorrect() throws Exception {
        IOUtils.getUserInputFromCommandLine("r8 8", 3);
    }


    private void verify(final String userInput, final int expectedLength, final String ... splitedRes) throws Exception {
        final String[] res = IOUtils.getUserInputFromCommandLine(userInput, expectedLength);
        for (int i = 0; i < expectedLength; i++) {
            assertEquals(splitedRes[i], res[i]);
        }
    }

}