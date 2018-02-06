package com.hankerzheng.minesweep.cli.view;

public interface UserInterface {
    void display();

    default void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    default void updateView() {
        clearConsole();
        display();
    }

    default void appendView() {
        display();
    }
}
