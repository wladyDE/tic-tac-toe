package com.tictactoe.example.console.validation.impl;

import com.tictactoe.example.console.validation.ConsoleInputValidation;
import com.tictactoe.example.game.GameContext;

public class CoordinatesInputValidation implements ConsoleInputValidation {

    @Override
    public boolean isValidInput(String userInput, GameContext gameContext) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
