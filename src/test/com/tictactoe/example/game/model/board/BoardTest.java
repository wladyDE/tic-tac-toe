package com.tictactoe.example.game.model.board;

import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.game.model.PlayerTurn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class BoardTest {

    private static final int COORDINATE_X = 0;
    private static final int COORDINATE_Y = 0;
    private static final Cell CELL_VALUE = Cell.X;

    private final Board board = new Board();

    @Mock
    private GameNotificationConsole gameNotificationConsole;

    @Test
    void shouldSetExpectedValueToBoardCell() {
        board.setBoardValue(COORDINATE_X, COORDINATE_Y, CELL_VALUE);
        Cell[][] boardCells = board.getGameBoard();

        assertEquals(CELL_VALUE, boardCells[COORDINATE_X][COORDINATE_Y]);
    }

    @Test
    void shouldSetExpectedPlayerTurnValueToBoardCell() {
        PlayerTurn expectedPlayerTurn = new PlayerTurn(COORDINATE_X, COORDINATE_Y);
        board.setBoardValue(expectedPlayerTurn, CELL_VALUE);
        Cell[][] boardCells = board.getGameBoard();

        assertEquals(CELL_VALUE, boardCells[COORDINATE_X][COORDINATE_Y]);
    }

    @Test
    void shouldPrintBoard() {
        doNothing().when(gameNotificationConsole).sendMessage(anyString());

        board.printBoard(gameNotificationConsole);

        verify(gameNotificationConsole).sendMessage("  | 0 | 1 | 2 |" + "\n--+---+---+---+");

        verify(gameNotificationConsole, times(12)).sendMessage(anyString(), eq(false));

        verify(gameNotificationConsole, times(3)).sendMessage("\n--+---+---+---+");
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 0", "0, 1", "2, 2", "2, 1", "1, 2", "0, 2", "2, 0", "1, 1"})
    void shouldReturnTrueWhenValidCell(int x, int y) {
        assertTrue(board.isValidCell(x, y));
    }

    @ParameterizedTest
    @CsvSource({"0, 3", "1, 4", "3, 1", "-1, 2", "4, 1", "9, 2", "0, 99"})
    void shouldReturnFalseAfterIncorrectCorrectInput(int x, int y) {
        assertFalse(board.isValidCell(x, y));
    }
}