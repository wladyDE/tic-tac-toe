package com.tictactoe.example.game.model;

import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.board.BoardStateResult;
import com.tictactoe.example.game.player.PlayerFactory;

public class GameModel {

    private final GameContext gameContext;

    public GameModel() {
        this.gameContext = new GameContext();
    }

    public GameModel(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public void startGame() {
        GameOpponent gameOpponent = gameContext.getUserInputScanner().getGameOpponent(gameContext);

        gameContext.getBoard().printBoard(gameContext.getGameNotificationConsole());
        BoardStateResult boardStateResult = PlayerFactory.getPlayer(gameOpponent).play(gameContext);

        gameContext.getGameNotificationConsole().sendMessage(boardStateResult.toString());
    }
}
