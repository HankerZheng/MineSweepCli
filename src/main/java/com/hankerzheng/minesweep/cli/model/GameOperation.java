package com.hankerzheng.minesweep.cli.model;

public enum GameOperation {
    CHECK_OPERATION,
    MARK_OPERATION,
    AROUND_OPERATION,
    UNMARK_OPERATION;

    static GameOperation parseOperation(String operation) {
        if (operation.equals("m") || operation.equals("mark")) {
            return MARK_OPERATION;
        }
        if (operation.equals("c") || operation.equals("check")) {
            return CHECK_OPERATION;
        }
        if (operation.equals("a") || operation.equals("around")) {
            return AROUND_OPERATION;
        }
        if (operation.equals("u") || operation.equals("unmark")) {
            return UNMARK_OPERATION;
        }

        throw new UnsupportedOperationException("Unsupported operation!!");
    }
}
