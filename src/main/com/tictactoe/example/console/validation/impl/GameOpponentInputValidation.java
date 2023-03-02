package com.tictactoe.example.console.validation.impl;

import com.tictactoe.example.console.validation.ConsoleInputValidation;
import com.tictactoe.example.game.GameContext;

public class GameOpponentInputValidation implements ConsoleInputValidation {

    @Override
    public boolean isValidInput(String userInput, GameContext gameContext) {
        return gameContext.getGameOpponents().stream()
                .anyMatch(gameOpponent -> gameOpponent.getOpponentVariant().equals(userInput));
    }
}
