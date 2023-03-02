package com.tictactoe.example.game.model.board;

public class BoardState {
    public BoardStateResult getCurrentBoardStateResult(Board board) {
        if (isGameWonForValue(board, Cell.X)) {
            return BoardStateResult.PLAYER_1_WON;
        }
        if (isGameWonForValue(board, Cell.O)) {
            return BoardStateResult.PLAYER_2_WON;
        }
        if (isBoardFulfilled(board)) {
            return BoardStateResult.DRAW;
        }

        return BoardStateResult.CONTINUE;
    }

    private boolean isGameWonForValue(Board board, Cell value) {
        Cell[][] gameBoardCells = board.getGameBoard();
        int boardLength = board.getBoardLength();

        for (int i = 0; i < boardLength; i++) {
            if (isHorizontalLineFulfilled(value, gameBoardCells[i])
                    || isVerticalLineFulfilled(value, gameBoardCells, i)) {
                return true;
            }
        }

        return isLeftCrossLineFulfilled(value, gameBoardCells) || isRightCrossLineFulfilled(value, gameBoardCells);
    }

    private boolean isRightCrossLineFulfilled(Cell value, Cell[][] gameBoardCells) {
        return gameBoardCells[2][0] == value && gameBoardCells[1][1] == value && gameBoardCells[0][2] == value;
    }

    private boolean isLeftCrossLineFulfilled(Cell value, Cell[][] gameBoardCells) {
        return gameBoardCells[0][0] == value && gameBoardCells[1][1] == value && gameBoardCells[2][2] == value;
    }

    private boolean isVerticalLineFulfilled(Cell value, Cell[][] gameBoardCells, int i) {
        return gameBoardCells[0][i] == value && gameBoardCells[1][i] == value && gameBoardCells[2][i] == value;
    }

    private boolean isHorizontalLineFulfilled(Cell value, Cell[] gameBoardCells) {
        return gameBoardCells[0] == value && gameBoardCells[1] == value && gameBoardCells[2] == value;
    }

    private boolean isBoardFulfilled(Board board) {
        Cell[][] gameBoardCells = board.getGameBoard();
        int boardLength = board.getBoardLength();

        for (int j = 0; j < boardLength; j++) {
            for (int i = 0; i < boardLength; i++) {
                if (gameBoardCells[i][j] == Cell.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }
}
