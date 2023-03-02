package com.tictactoe.example.console;

import com.tictactoe.example.console.exception.BadGameOpponentValueException;
import com.tictactoe.example.console.validation.ConsoleInputValidation;
import com.tictactoe.example.console.validation.impl.CoordinatesInputValidation;
import com.tictactoe.example.console.validation.impl.GameOpponentInputValidation;
import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.GameOpponent;
import com.tictactoe.example.game.model.PlayerTurn;
import com.tictactoe.example.game.model.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserInputScannerTest {
    private static final List<GameOpponent> PLAYERS = Collections.singletonList(GameOpponent.PLAYER);
    private static final String USER_INPUT = "User input";
    private static final GameOpponent EXPECTED_GAME_OPPONENT = GameOpponent.PLAYER;
    private static final String EXPECTED_COORDINATE = "1";
    private static final PlayerTurn EXPECTED_PLAYER_TURN = new PlayerTurn(1, 1);

    @Mock
    private Scanner scanner;
    @Mock
    private ConsoleInputValidation gameOpponentInputValidation = new GameOpponentInputValidation();
    @Mock
    private ConsoleInputValidation coordinatesInputValidation = new CoordinatesInputValidation();
    @Mock
    private GameContext gameContext;
    @Mock
    private GameNotificationConsole gameNotificationConsole;
    @Mock
    private Board board;

    private UserInputScanner userInputScanner;

    @BeforeEach
    void setUp() {
        when(board.isValidCell(any(Integer.class), any(Integer.class))).thenReturn(true);
        when(scanner.next()).thenReturn(EXPECTED_COORDINATE);
        when(gameContext.getBoard()).thenReturn(board);

        when(gameOpponentInputValidation.isValidInput(eq(USER_INPUT), any(GameContext.class))).thenReturn(true);
        when(scanner.nextLine()).thenReturn(USER_INPUT);
        doNothing().when(gameNotificationConsole).sendMessage(anyString());
        when(gameContext.getGameOpponents()).thenReturn(PLAYERS);
        when(gameContext.getGameNotificationConsole()).thenReturn(gameNotificationConsole);

        userInputScanner = new UserInputScanner(scanner, gameOpponentInputValidation, coordinatesInputValidation);
    }

    @Test
    public void shouldReturnExpectedGameOpponent() {
        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString())).thenReturn(EXPECTED_GAME_OPPONENT);

            GameOpponent actualGameOpponent = userInputScanner.getGameOpponent(gameContext);

            assertEquals(EXPECTED_GAME_OPPONENT, actualGameOpponent, "Should be equal!");
        }
    }

    @Test
    public void shouldReturnExpectedGameOpponentAfterUserIncorrectInput() {
        when(gameOpponentInputValidation.isValidInput(eq(USER_INPUT), any(GameContext.class)))
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString())).thenReturn(EXPECTED_GAME_OPPONENT);

            GameOpponent actualGameOpponent = userInputScanner.getGameOpponent(gameContext);

            assertEquals(EXPECTED_GAME_OPPONENT, actualGameOpponent, "Should be equal!");
        }
    }

    @Test
    public void shouldNotifyUserExpectedTimesAfterUserIncorrectInput() {
        final String expectedMessage = "Please enter valid opponent value: 2 - another player";

        when(gameOpponentInputValidation.isValidInput(eq(USER_INPUT), any(GameContext.class)))
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString())).thenReturn(EXPECTED_GAME_OPPONENT);

            userInputScanner.getGameOpponent(gameContext);

            verify(gameNotificationConsole, times(2)).sendMessage(expectedMessage);
        }
    }

    @Test
    public void shouldRequestUserInputExpectedTimesAfterUserIncorrectInput() {
        when(gameOpponentInputValidation.isValidInput(eq(USER_INPUT), any(GameContext.class)))
                .thenReturn(false)
                .thenReturn(true);

        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString())).thenReturn(EXPECTED_GAME_OPPONENT);

            userInputScanner.getGameOpponent(gameContext);

            verify(scanner, times(2)).nextLine();
        }
    }

    @Test
    public void shouldSendChooseOpponentMessageToUser() {
        final String expectedMessage = "Choose your game: press 2 to play with another player";

        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString())).thenReturn(EXPECTED_GAME_OPPONENT);

            userInputScanner.getGameOpponent(gameContext);

            verify(gameNotificationConsole).sendMessage(expectedMessage);
        }
    }

    @Test
    public void shouldReturnExpectedGameOpponentWhenBadGameOpponentValueExceptionOccurred() {
        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString()))
                    .thenThrow(BadGameOpponentValueException.class)
                    .thenReturn(EXPECTED_GAME_OPPONENT);

            GameOpponent actualGameOpponent = userInputScanner.getGameOpponent(gameContext);

            assertEquals(EXPECTED_GAME_OPPONENT, actualGameOpponent, "Should be equal");
        }
    }

    @Test
    public void shouldNotifyUserExpectedTimesWhenBadGameOpponentValueExceptionOccurred() {
        final String expectedMessage = "Please enter valid opponent value: 2 - another player";

        try (MockedStatic<GameOpponent> mocked = mockStatic(GameOpponent.class)) {
            mocked.when(() -> GameOpponent.getGameOpponentFromVariant(anyString()))
                    .thenThrow(BadGameOpponentValueException.class)
                    .thenThrow(BadGameOpponentValueException.class)
                    .thenReturn(EXPECTED_GAME_OPPONENT);

            userInputScanner.getGameOpponent(gameContext);

            verify(gameNotificationConsole, times(2)).sendMessage(expectedMessage);
        }
    }

    @Test
    public void shouldReturnExpectedPlayerTurn() {
        PlayerTurn actualPlayerTurn = userInputScanner.getPlayerTurn(gameContext);

        assertAll(
                () -> assertEquals(EXPECTED_PLAYER_TURN.getX(), actualPlayerTurn.getX(), "Should be equal!"),
                () -> assertEquals(EXPECTED_PLAYER_TURN.getY(), actualPlayerTurn.getY(), "Should be equal!")
        );
    }
}