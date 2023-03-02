package com.tictactoe.example.game.model.board;

public enum BoardStateResult {
    PLAYER_1_WON("Player 1 won!"),
    PLAYER_2_WON("Player 2 won!"),
    DRAW("It is draw this time..."),
    CONTINUE("ERROR!");

    private final String formattedMessage;

    BoardStateResult(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    @Override
    public String toString() {
        return formattedMessage;
    }
}
