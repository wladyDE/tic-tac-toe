package com.tictactoe.example.game.model.board;

public enum Cell {
    EMPTY("-"),
    X("X"),
    O("O");

    private final String cellValue;

    Cell(String cellValue) {
        this.cellValue = cellValue;
    }

    @Override
    public String toString() {
        return cellValue;
    }
}
