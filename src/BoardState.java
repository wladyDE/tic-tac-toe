public class BoardState {
    public int checkWin(Cell[][] board){
        if (checkWin(board, Cell.X)){
            return 10;
        } else if (checkWin(board, Cell.O)){
            return -10;
        } else if (isFullField(board)){
            return -1;
        }
        return 0;
    }

    public void printWin(int value){
        if (value == 10){
            System.out.println("You Won!");
        } else if(value == -10){
            System.out.println("You lost!");
        } else {
            System.out.println("Draw!");
        }
    }

    private boolean checkWin(Cell[][] gameBoard, Cell value){
        for (int i = 0; i < 3; i++){
            if ((gameBoard[i][0] == value && gameBoard[i][1] == value && gameBoard[i][2] == value) ||
                    (gameBoard[0][i] == value && gameBoard[1][i] == value && gameBoard[2][i] == value)){
                return true;
            }
        }
        return (gameBoard[0][0] == value && gameBoard[1][1] == value && gameBoard[2][2] == value) ||
                (gameBoard[2][0] == value && gameBoard[1][1] == value && gameBoard[0][2] == value);
    }

    private boolean isFullField(Cell[][] gameBoard) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][j] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
