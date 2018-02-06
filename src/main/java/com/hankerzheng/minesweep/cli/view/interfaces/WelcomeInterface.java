package com.hankerzheng.minesweep.cli.view.interfaces;

public class WelcomeInterface implements UserInterface {

    private static final String WELCOM_INFO =
            "WELCOME TO MINESWEEP\n" +
                    "Please input the height, width and the # of mines to continue...\n" +
                    "Or just press RETURN to load the default configurations...\n";

    public void display() {
        System.out.println(WELCOM_INFO);
    }
}
