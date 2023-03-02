package com.tictactoe.example.game.player;

import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.console.UserInputScanner;
import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.PlayerTurn;
import com.tictactoe.example.game.model.board.Board;
import com.tictactoe.example.game.model.board.BoardStateResult;
import com.tictactoe.example.game.model.board.Cell;


public abstract class GamePlayer {
    public abstract BoardStateResult play(GameContext gameContext);

    protected void makePlayerTurn(GameContext gameContext, Cell cellValue) {
        UserInputScanner userInputScanner = gameContext.getUserInputScanner();
        GameNotificationConsole gameNotificationConsole = gameContext.getGameNotificationConsole();
        Board board = gameContext.getBoard();

        PlayerTurn playerTurn = userInputScanner.getPlayerTurn(gameContext);

        board.setBoardValue(playerTurn, cellValue);
        board.printBoard(gameNotificationConsole);
    }
}
