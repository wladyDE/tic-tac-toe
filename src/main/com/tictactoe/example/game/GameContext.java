package com.tictactoe.example.game;

import com.tictactoe.example.game.model.GameOpponent;
import com.tictactoe.example.game.model.board.BoardState;
import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.console.UserInputScanner;
import com.tictactoe.example.game.model.board.Board;

import java.util.Arrays;
import java.util.List;

public class GameContext {
    private final List<GameOpponent> gameOpponents;
    private final GameNotificationConsole gameNotificationConsole;
    private final UserInputScanner userInputScanner;
    private final Board board;
    private final BoardState boardState;

    public GameContext() {
        this.board = new Board();
        this.boardState = new BoardState();
        this.userInputScanner = new UserInputScanner();
        this.gameOpponents = Arrays.asList(GameOpponent.values());
        this.gameNotificationConsole = new GameNotificationConsole();
    }

    public GameNotificationConsole getGameNotificationConsole() {
        return gameNotificationConsole;
    }

    public List<GameOpponent> getGameOpponents() {
        return gameOpponents;
    }

    public UserInputScanner getUserInputScanner() {
        return userInputScanner;
    }

    public Board getBoard() {
        return board;
    }

    public BoardState getBoardState() {
        return boardState;
    }
}
