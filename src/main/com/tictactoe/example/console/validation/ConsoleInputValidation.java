package com.tictactoe.example.console.validation;

import com.tictactoe.example.game.GameContext;

public interface ConsoleInputValidation {
    boolean isValidInput(String userInput, GameContext gameContext);
}
