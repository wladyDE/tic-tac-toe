package com.tictactoe.example.game.player;

import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.AI;
import com.tictactoe.example.game.model.board.BoardState;
import com.tictactoe.example.game.model.board.Cell;
import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.game.model.board.Board;
import com.tictactoe.example.game.model.board.BoardStateResult;

public class AiPlayer extends GamePlayer {
    private final AI ai;

    public AiPlayer() {
        this.ai = new AI();
    }

    public AiPlayer(AI ai) {
        this.ai = ai;
    }

    @Override
    public BoardStateResult play(GameContext gameContext) {
        Board board = gameContext.getBoard();
        BoardState boardState = gameContext.getBoardState();
        BoardStateResult currentBoardStateResult = BoardStateResult.CONTINUE;
        GameNotificationConsole gameNotificationConsole = gameContext.getGameNotificationConsole();

        while (currentBoardStateResult == BoardStateResult.CONTINUE) {
            makePlayerTurn(gameContext, Cell.X);
            currentBoardStateResult = boardState.getCurrentBoardStateResult(board);

            if (currentBoardStateResult != BoardStateResult.CONTINUE) {
                break;
            }

            currentBoardStateResult = aiTurn(ai, board, boardState, gameNotificationConsole);
        }

        return currentBoardStateResult;
    }

    private BoardStateResult aiTurn(AI ai, Board board, BoardState boardState,
                                    GameNotificationConsole gameNotificationConsole) {
        gameNotificationConsole.sendMessage("\n AI makes turn \n");
        ai.makeTurn(board.getGameBoard());
        board.printBoard(gameNotificationConsole);

        return boardState.getCurrentBoardStateResult(board);
    }
}
