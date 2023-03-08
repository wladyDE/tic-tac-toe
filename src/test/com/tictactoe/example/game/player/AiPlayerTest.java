package com.tictactoe.example.game.player;

import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.console.UserInputScanner;
import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.AI;
import com.tictactoe.example.game.model.PlayerTurn;
import com.tictactoe.example.game.model.board.Board;
import com.tictactoe.example.game.model.board.BoardState;
import com.tictactoe.example.game.model.board.BoardStateResult;
import com.tictactoe.example.game.model.board.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AiPlayerTest {
    private final static BoardStateResult EXPECTED_BOARD_STATE_RESULT = BoardStateResult.PLAYER_1_WON;
    private final static Cell[][] GAME_BOARD_CELLS = new Cell[][] {{ Cell.O, Cell.X }};
    private final static PlayerTurn PLAYER_TURN = new PlayerTurn(1,1);

    @Mock
    private GameContext gameContext;
    @Mock
    private Board board;
    @Mock
    private BoardState boardState;
    @Mock
    private GameNotificationConsole gameNotificationConsole;
    @Mock
    private UserInputScanner userInputScanner;
    @Mock
    private AI ai;

    private AiPlayer aiPlayer;

    @BeforeEach
    void setUp() {
        when(boardState.getCurrentBoardStateResult(board)).thenReturn(BoardStateResult.PLAYER_1_WON);

        doNothing().when(board).printBoard(gameNotificationConsole);
        doNothing().when(board).setBoardValue(any(PlayerTurn.class), any(Cell.class));
        when(board.getGameBoard()).thenReturn(GAME_BOARD_CELLS);
        when(userInputScanner.getPlayerTurn(any(GameContext.class))).thenReturn(PLAYER_TURN);
        when(gameContext.getUserInputScanner()).thenReturn(userInputScanner);

        when(gameContext.getGameNotificationConsole()).thenReturn(gameNotificationConsole);
        when(gameContext.getBoardState()).thenReturn(boardState);
        when(gameContext.getBoard()).thenReturn(board);

        aiPlayer = new AiPlayer(ai);
    }

    @Test
    public void shouldReturnExpectedBoardStateResult() {
            BoardStateResult actualBoardStateResult = aiPlayer.play(gameContext);

            assertEquals(EXPECTED_BOARD_STATE_RESULT, actualBoardStateResult, "Should be equal!");
    }

    @Test
    public void shouldReturnExpectedBoardStateResultAfter() {
        when(boardState.getCurrentBoardStateResult(board))
                .thenReturn(BoardStateResult.CONTINUE)
                .thenReturn(BoardStateResult.CONTINUE)
                .thenReturn(BoardStateResult.PLAYER_1_WON);
        doNothing().when(gameNotificationConsole).sendMessage(anyString());
        doNothing().when(ai).makeTurn(eq(GAME_BOARD_CELLS));

        BoardStateResult actualBoardStateResult = aiPlayer.play(gameContext);

        assertEquals(EXPECTED_BOARD_STATE_RESULT, actualBoardStateResult, "Should be equal!");
    }
}