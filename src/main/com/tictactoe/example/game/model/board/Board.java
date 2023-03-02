package com.tictactoe.example.game.model.board;

import com.tictactoe.example.console.GameNotificationConsole;
import com.tictactoe.example.game.model.PlayerTurn;

public class Board {
    private static final int BOARD_LENGTH = 3;

    private final Cell[][] gameBoard = new Cell[BOARD_LENGTH][BOARD_LENGTH];

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public Board() {
        initialiseBoard();
    }

    public void setBoardValue(PlayerTurn playerTurn, Cell fieldValue){
        setBoardValue(playerTurn.getX(), playerTurn.getY(), fieldValue);
    }

    public void setBoardValue(int x, int y, Cell fieldValue){
        gameBoard[x][y] = fieldValue;
    }

    public int getBoardLength() {
        return BOARD_LENGTH;
    }

    public boolean isValidCell(int x, int y){
        return x >= 0 && x < BOARD_LENGTH && y >= 0 && y < BOARD_LENGTH && gameBoard[x][y] == Cell.EMPTY;
    }

    public void printBoard(GameNotificationConsole gameNotificationConsole) {
        gameNotificationConsole.sendMessage("  | 0 | 1 | 2 |" + "\n--+---+---+---+");
        for (int j = 0; j < BOARD_LENGTH; j++) {
            gameNotificationConsole.sendMessage(j + " | ", false);

            for (int i = 0; i < BOARD_LENGTH; i++) {
                gameNotificationConsole.sendMessage(gameBoard[i][j] + " | ", false);
            }

            gameNotificationConsole.sendMessage("\n--+---+---+---+");
        }
    }

    private void initialiseBoard() {
        for (int j = 0; j < BOARD_LENGTH; j++) {
            for (int i = 0; i < BOARD_LENGTH; i++) {
                gameBoard[i][j] = Cell.EMPTY;
            }
        }
    }
}
