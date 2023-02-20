public class Field {
    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private final Cell[][] gameField = new Cell[WIDTH][HEIGHT];

    public Cell[][] getGameField() {
        return gameField;
    }

    public void initialiseField() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                gameField[i][j] = Cell.EMPTY;
            }
        }
    }

    public void printField() {
        System.out.println("     1   2   3");
        for (int j = 0; j < HEIGHT; j++) {
            System.out.print(j+1 + "   ");
            for (int i = 0; i < WIDTH; i++) {
                System.out.print(getSymbol(gameField[i][j]) + " ");
            }
            System.out.println("\n");
        }
    }

    public void setFieldValue(int x, int y, Cell fieldValue){
        gameField[x][y] = fieldValue;
    }

    public boolean isValidCell(int x, int y){
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT || gameField[x][y] != Cell.EMPTY) {
            System.out.println("Your coordinates are incorrect!");
            return false;
        }
        return true;
    }

    public boolean checkWin(){
        if (checkWin(Cell.X)){
            System.out.println("You won, Congratulations");
            return true;
        } else if (checkWin(Cell.O)){
            System.out.println("You lost :(");
            return true;
        } else if (isFullField()){
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    private boolean checkWin(Cell value){
        for (int i = 0; i < WIDTH; i++){
            if ((gameField[i][0] == value && gameField[i][1] == value && gameField[i][2] == value) ||
                    (gameField[0][i] == value && gameField[1][i] == value && gameField[2][i] == value)){
                return true;
            }
        }
        return (gameField[0][0] == value && gameField[1][1] == value && gameField[2][2] == value) ||
                (gameField[2][0] == value && gameField[1][1] == value && gameField[0][2] == value);
    }

    private boolean isFullField() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (gameField[i][j] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getSymbol(Cell value) {
        if (value == Cell.EMPTY) {
            return "[  ]";
        } else if (value == Cell.X) {
            return " X ";
        }
        return " O ";
    }
}
