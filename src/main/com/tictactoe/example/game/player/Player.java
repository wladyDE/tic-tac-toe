package com.tictactoe.example.game.player;

import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.board.Board;
import com.tictactoe.example.game.model.board.BoardState;
import com.tictactoe.example.game.model.board.BoardStateResult;
import com.tictactoe.example.game.model.board.Cell;

public class Player extends GamePlayer {

    @Override
    public BoardStateResult play(GameContext gameContext) {
        Board board = gameContext.getBoard();
        BoardState boardState = gameContext.getBoardState();
        BoardStateResult currentBoardStateResult = BoardStateResult.CONTINUE;

        while (currentBoardStateResult == BoardStateResult.CONTINUE) {
            makePlayerTurn(gameContext, Cell.X);
            currentBoardStateResult = boardState.getCurrentBoardStateResult(board);

            if (currentBoardStateResult != BoardStateResult.CONTINUE) {
                break;
            }

            currentBoardStateResult = makeOpponentTurn(gameContext);
        }

        return currentBoardStateResult;
    }

    private BoardStateResult makeOpponentTurn(GameContext gameContext) {
        gameContext.getGameNotificationConsole().sendMessage("\n Another player makes turn \n");
        makePlayerTurn(gameContext, Cell.O);

        return gameContext.getBoardState().getCurrentBoardStateResult(gameContext.getBoard());
    }
}
