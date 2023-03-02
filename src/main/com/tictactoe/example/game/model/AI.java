package com.tictactoe.example.game.model;

import com.tictactoe.example.game.model.board.Cell;

public class AI {
    public void makeTurn(Cell[][] gameField) {
        if (gameField[1][1] != Cell.EMPTY) {
            Cell[][] tempBoard = copyBoard(gameField);
            checkBoard(gameField, Cell.O); // check if O can win

            if (compareBoards(gameField, tempBoard)) {
                Cell[][] tempBoard2 = copyBoard(gameField);
                checkBoard(gameField, Cell.X);  // check if X can win and stop it

                if (compareBoards(gameField, tempBoard2)) {
                    Cell[][] tempBoard3 = copyBoard(gameField);
                    setCorner(gameField);      // if threat does not exist -> take corner

                    if (compareBoards(tempBoard3, gameField)) {
                        for (int j = 0; j < 3; j++) {
                            for (int i = 0; i < 3; i++) {
                                if (gameField[i][j] == Cell.EMPTY) {
                                    gameField[i][j] = Cell.O;    // take a random cell
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            gameField[1][1] = Cell.O;
        }
    }

    private boolean compareBoards(Cell[][] firstBoard, Cell[][] secondBoard) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (firstBoard[i][j] != secondBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Cell[][] copyBoard(Cell[][] board) {
        Cell[][] newBoard = new Cell[3][3];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    private void setCorner(Cell[][] gameField) {
        if (gameField[0][0] == Cell.EMPTY) {
            gameField[0][0] = Cell.O;
            return;
        }
        if (gameField[2][2] == Cell.EMPTY) {
            gameField[2][2] = Cell.O;
            return;
        }
        if (gameField[0][2] == Cell.EMPTY) {
            gameField[0][2] = Cell.O;
            return;
        }
        if (gameField[2][0] == Cell.EMPTY) {
            gameField[2][0] = Cell.O;
        }
    }

    private void checkBoard(Cell[][] board, Cell value) {
        if (board[0][2] == value && board[0][1] == value && board[0][0] == Cell.EMPTY) {
            board[0][0] = Cell.O;
            return;
        }
        if (board[0][0] == value && board[0][2] == value && board[0][1] == Cell.EMPTY) {
            board[0][1] = Cell.O;
            return;
        }
        if (board[0][0] == value && board[0][1] == value && board[0][2] == Cell.EMPTY) {
            board[0][2] = Cell.O;
            return;
        }
        if (board[1][2] == value && board[1][1] == value && board[1][0] == Cell.EMPTY) {
            board[1][0] = Cell.O;
            return;
        }
        if (board[1][0] == value && board[1][2] == value && board[1][1] == Cell.EMPTY) {
            board[1][1] = Cell.O;
            return;
        }
        if (board[1][0] == value && board[1][1] == value && board[1][2] == Cell.EMPTY) {
            board[1][2] = Cell.O;
            return;
        }
        if (board[2][2] == value && board[2][1] == value && board[2][0] == Cell.EMPTY) {
            board[2][0] = Cell.O;
            return;
        }
        if (board[2][0] == value && board[2][2] == value && board[2][1] == Cell.EMPTY) {
            board[2][1] = Cell.O;
            return;
        }
        if (board[2][0] == value && board[2][1] == value && board[2][2] == Cell.EMPTY) {
            board[2][2] = Cell.O;
            return;
        }

        if (board[0][0] == value && board[1][0] == value && board[2][0] == Cell.EMPTY) {
            board[2][0] = Cell.O;
            return;
        }
        if (board[2][0] == value && board[1][0] == value && board[0][0] == Cell.EMPTY) {
            board[0][0] = Cell.O;
            return;
        }
        if (board[2][0] == value && board[0][0] == value && board[1][0] == Cell.EMPTY) {
            board[1][0] = Cell.O;
            return;
        }
        if (board[0][1] == value && board[1][1] == value && board[2][1] == Cell.EMPTY) {
            board[2][1] = Cell.O;
            return;
        }
        if (board[2][1] == value && board[1][1] == value && board[0][1] == Cell.EMPTY) {
            board[0][1] = Cell.O;
            return;
        }
        if (board[2][1] == value && board[0][1] == value && board[1][1] == Cell.EMPTY) {
            board[1][1] = Cell.O;
            return;
        }
        if (board[0][2] == value && board[1][2] == value && board[2][2] == Cell.EMPTY) {
            board[2][2] = Cell.O;
            return;
        }
        if (board[2][2] == value && board[1][2] == value && board[0][2] == Cell.EMPTY) {
            board[0][2] = Cell.O;
            return;
        }
        if (board[2][2] == value && board[0][2] == value && board[1][2] == Cell.EMPTY) {
            board[1][2] = Cell.O;
            return;
        }

        if (board[0][0] == value && board[1][1] == value && board[2][2] == Cell.EMPTY) {
            board[2][2] = Cell.O;
            return;
        }
        if (board[2][2] == value && board[1][1] == value && board[0][0] == Cell.EMPTY) {
            board[0][0] = Cell.O;
            return;
        }
        if (board[0][0] == value && board[2][2] == value && board[1][1] == Cell.EMPTY) {
            board[1][1] = Cell.O;
            return;
        }

        if (board[2][0] == value && board[1][1] == value && board[0][2] == Cell.EMPTY) {
            board[0][2] = Cell.O;
            return;
        }
        if (board[0][2] == value && board[1][1] == value && board[2][0] == Cell.EMPTY) {
            board[2][0] = Cell.O;
            return;
        }
        if (board[2][0] == value && board[0][2] == value && board[1][1] == Cell.EMPTY) {
            board[1][1] = Cell.O;
        }
    }
}


