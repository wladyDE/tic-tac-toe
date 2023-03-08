package com.tictactoe.example.console.validation.impl;

import com.tictactoe.example.console.validation.ConsoleInputValidation;
import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.GameOpponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameOpponentInputValidationTest {
    private static final List<GameOpponent> GAME_OPPONENTS = List.of(GameOpponent.AI, GameOpponent.PLAYER);

    @Mock
    private GameContext gameContext;

    private ConsoleInputValidation inputValidation;

    @BeforeEach
    void setUp() {
        when(gameContext.getGameOpponents()).thenReturn(GAME_OPPONENTS);

        inputValidation = new GameOpponentInputValidation();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void shouldReturnTrueWhenCorrectInput(String input){
        assertTrue(inputValidation.isValidInput(input, gameContext));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", ".", "-"})
    void shouldReturnFalseAfterIncorrectCorrectInput(String input) {
        assertFalse(inputValidation.isValidInput(input, gameContext));
    }
}