public class Board {
    private static final int N = 3;
    private final Cell[][] gameBoard = new Cell[N][N];

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public Board() {
        initialiseBoard();
    }

    public void setBoardValue(int x, int y, Cell fieldValue){
        gameBoard[x][y] = fieldValue;
    }

    public boolean isValidCell(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N || gameBoard[x][y] != Cell.EMPTY) {
            System.out.println("Your coordinates are incorrect!");
            return false;
        }
        return true;
    }

    public void printBoard() {
        System.out.println("  | 0 | 1 | 2 |" + "\n--+---+---+---+");
        for (int j = 0; j < N; j++) {
            System.out.print(j + " | ");
            for (int i = 0; i < N; i++) {
                System.out.print(getSymbol(gameBoard[i][j]) + " | ");
            }
            System.out.println("\n--+---+---+---+");
        }
    }

    private void initialiseBoard() {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                gameBoard[i][j] = Cell.EMPTY;
            }
        }
    }

    private String getSymbol(Cell value) {
        if (value == Cell.EMPTY) {
            return "-";
        } else if (value == Cell.X) {
            return "X";
        }
        return "O";
    }
}
