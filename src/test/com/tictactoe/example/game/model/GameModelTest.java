package com.tictactoe.example.game.model;

import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.console.UserInputScanner;
import com.tictactoe.example.game.util.Acceptable;
import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.board.Board;
import com.tictactoe.example.game.model.board.BoardStateResult;
import com.tictactoe.example.game.player.GamePlayer;
import com.tictactoe.example.game.player.Player;
import com.tictactoe.example.game.player.PlayerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameModelTest {

    private final static GameOpponent GAME_OPPONENT = GameOpponent.PLAYER;
    private final static BoardStateResult DRAW_RESULT = BoardStateResult.DRAW;

    @Mock
    private GameContext gameContext;
    @Mock
    private UserInputScanner userInputScanner;
    @Mock
    private Board board;
    @Mock
    private GameNotificationConsole gameNotificationConsole;
    @Mock
    private GamePlayer gamePlayer = new Player();

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        doReturn(gameNotificationConsole).when(gameContext).getGameNotificationConsole();
        when(gamePlayer.play(any(GameContext.class))).thenReturn(DRAW_RESULT);

        doNothing().when(gameNotificationConsole).sendMessage(DRAW_RESULT.toString());
        doNothing().when(board).printBoard(any(GameNotificationConsole.class));
        when(gameContext.getBoard()).thenReturn(board);
        when(userInputScanner.getGameOpponent(any(GameContext.class))).thenReturn(GAME_OPPONENT);
        when(gameContext.getUserInputScanner()).thenReturn(userInputScanner);

        gameModel = new GameModel(gameContext);
    }

    @DisplayName("Should get opponent")
    @Test
    void shouldGetGameOpponentFromConsole() {
        verifyCallInStaticScope(() -> gameModel.startGame(),
                () -> verify(userInputScanner).getGameOpponent(any(GameContext.class)));
    }

    @DisplayName("Should print board")
    @Test
    void shouldPrintGameBoard() {
        verifyCallInStaticScope(() -> gameModel.startGame(),
                () -> verify(board).printBoard(any(GameNotificationConsole.class)));
    }

    @DisplayName("Should start game")
    @Test
    void shouldStartGame() {
        verifyCallInStaticScope(() -> gameModel.startGame(),
                () -> verify(gamePlayer).play(any(GameContext.class)));
    }

    @DisplayName("Should print game result message")
    @Test
    void shouldPrintGameResultMessage() {
        verifyCallInStaticScope(() -> gameModel.startGame(),
                () -> verify(gameNotificationConsole).sendMessage(DRAW_RESULT.toString()));
    }

    private void verifyCallInStaticScope(Acceptable callMethod, Acceptable verifyCall) {
        try (MockedStatic<PlayerFactory> mocked = mockStatic(PlayerFactory.class)) {
            mocked.when(() -> PlayerFactory.getPlayer(any(GameOpponent.class))).thenReturn(gamePlayer);

            gameModel.startGame();
        }
    }
}