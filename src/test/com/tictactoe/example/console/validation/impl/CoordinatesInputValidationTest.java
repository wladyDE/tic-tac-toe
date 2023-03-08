package com.tictactoe.example.console.validation.impl;

import com.tictactoe.example.console.validation.ConsoleInputValidation;
import com.tictactoe.example.game.GameContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CoordinatesInputValidationTest {
    @Mock
    private GameContext gameContext;

    private ConsoleInputValidation inputValidation;

    @BeforeEach
    void setUp(){
        inputValidation = new CoordinatesInputValidation();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void shouldReturnTrueAfterCorrectInput(String input) {
        assertTrue(inputValidation.isValidInput(input, gameContext));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", ".", "-"})
    void shouldReturnFalseAfterIncorrectCorrectInput(String input) {
        assertFalse(inputValidation.isValidInput(input, gameContext));
    }
}