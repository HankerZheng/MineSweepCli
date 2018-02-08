package com.hankerzheng.minesweep.cli.view.interfaces;

public interface UserInterface {
    void display();

    default void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    default void updateView() {
        clear();
        display();
    }

    default void appendView() {
        display();
    }
}
