package com.tictactoe.example.game.player;

import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.console.UserInputScanner;
import com.tictactoe.example.game.GameContext;
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
class PlayerTest {
    @Mock
    private GameContext gameContext;
    @Mock
    private Board board;
    @Mock
    private BoardState boardState;
    @Mock
    private UserInputScanner userInputScanner;
    @Mock
    private GameNotificationConsole gameNotificationConsole;
    @Mock
    private PlayerTurn playerTurn;

    private GamePlayer player;

    @BeforeEach
    void setUp() {
        doNothing().when(gameNotificationConsole).sendMessage(anyString());

        doNothing().when(board).printBoard(gameNotificationConsole);
        doNothing().when(board).setBoardValue(playerTurn, Cell.O);
        doNothing().when(board).setBoardValue(playerTurn, Cell.X);
        when(userInputScanner.getPlayerTurn(any(GameContext.class))).thenReturn(playerTurn);
        when(gameContext.getGameNotificationConsole()).thenReturn(gameNotificationConsole);
        when(gameContext.getUserInputScanner()).thenReturn(userInputScanner);

        when(gameContext.getBoardState()).thenReturn(boardState);
        when(gameContext.getBoard()).thenReturn(board);

        player = new Player();
    }

    @Test
    public void shouldReturnExpectedBoardStateResult() {
        when(boardState.getCurrentBoardStateResult(board)).thenReturn(BoardStateResult.PLAYER_1_WON);
        final BoardStateResult expectedBoardStateResult = BoardStateResult.PLAYER_1_WON;

        BoardStateResult actualBoardStateResult = player.play(gameContext);

        assertEquals(expectedBoardStateResult, actualBoardStateResult, "Should be equal!");
    }

    @Test
    public void shouldReturnExpectedBoardStateResultAfterTwoTurns() {
        when(boardState.getCurrentBoardStateResult(board))
                .thenReturn(BoardStateResult.CONTINUE)
                .thenReturn(BoardStateResult.CONTINUE)
                .thenReturn(BoardStateResult.PLAYER_1_WON);
        final BoardStateResult expectedBoardStateResult = BoardStateResult.PLAYER_1_WON;

        BoardStateResult actualBoardStateResult = player.play(gameContext);

        assertEquals(expectedBoardStateResult, actualBoardStateResult, "Should be equal!");
    }
}