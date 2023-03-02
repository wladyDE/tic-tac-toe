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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AiPlayerTest {
    private final static BoardStateResult EXPECTED_BOARD_STATE_RESULT = BoardStateResult.PLAYER_1_WON;

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
    private PlayerTurn playerTurn;
    @Mock
    private AI ai;

    private AiPlayer aiPlayer;

    @BeforeEach
    void setUp() {
        when(boardState.getCurrentBoardStateResult(board)).thenReturn(BoardStateResult.PLAYER_1_WON);

        doNothing().when(board).printBoard(gameNotificationConsole);
        doNothing().when(board).setBoardValue(playerTurn, Cell.O);
        when(userInputScanner.getPlayerTurn(any(GameContext.class))).thenReturn(playerTurn);
        when(gameContext.getUserInputScanner()).thenReturn(userInputScanner);

        when(gameContext.getGameNotificationConsole()).thenReturn(gameNotificationConsole);
        when(gameContext.getBoardState()).thenReturn(boardState);
        when(gameContext.getBoard()).thenReturn(board);

        aiPlayer = new AiPlayer();
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
        when(board.getGameBoard()).thenReturn(null);
        doNothing().when(ai).makeTurn(any());

        when(boardState.getCurrentBoardStateResult(board))
                .thenReturn(BoardStateResult.CONTINUE)
                .thenReturn(BoardStateResult.CONTINUE);

        BoardStateResult actualBoardStateResult = aiPlayer.play(gameContext);

        assertEquals(EXPECTED_BOARD_STATE_RESULT, actualBoardStateResult, "Should be equal!");
    }
}