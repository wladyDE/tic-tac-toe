package com.tictactoe.example.game.model;

import com.tictactoe.example.console.exception.BadGameOpponentValueException;
import java.util.Arrays;

public enum GameOpponent {
    AI("1", "AI"),
    PLAYER("2", "another player");

    private final String opponentVariant;
    private final String formattedName;

    GameOpponent(String opponentVariant, String formattedName) {
        this.opponentVariant = opponentVariant;
        this.formattedName = formattedName;
    }

    public String getOpponentVariant() {
        return opponentVariant;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public static GameOpponent getGameOpponentFromVariant(String opponentVariant) throws BadGameOpponentValueException {
        return Arrays.stream(values())
                .filter(gameOpponent -> gameOpponent.getOpponentVariant().equals(opponentVariant))
                .findFirst()
                .orElseThrow(() -> new BadGameOpponentValueException(
                        String.format("Bad value for game opponent: %s", opponentVariant)));
    }
}
