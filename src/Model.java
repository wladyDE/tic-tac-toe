import java.util.Scanner;

public class Model {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final Field gameField = new Field();

    public void startGame() {
        System.out.println("Choose your game: press 1 to play with AI and 2 to play with another Player");
        while (!SCANNER.hasNext("[12]")) {
            SCANNER.nextLine();
            System.out.println("Choose your game: press 1 to play with AI and 2 to play with another Player");
        }
        if ("1".equals(SCANNER.nextLine())) {
            playAgainstAI();
        } else {
            playAgainstPlayer();
        }
    }

    private void playAgainstPlayer() {
        gameField.initialiseField();
        gameField.printField();
        while (!gameField.checkWin()) {
            makeTurn(Cell.X);
            if (gameField.checkWin()){
                break;
            }
            makeTurn(Cell.O);
            gameField.printField();
        }
    }

    private void playAgainstAI() {
        AI ai = new AI();
        gameField.initialiseField();
        gameField.printField();
        while (!gameField.checkWin()) {
            makeTurn(Cell.X);
            if (gameField.checkWin()){
                break;
            }
            ai.makeTurn(gameField.getGameField());
            gameField.printField();
        }
    }

    private void makeTurn(Cell value) {
        int x, y;
        do {
            System.out.println("Enter the coordinates of your next Turn (X and Y)");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!gameField.isValidCell(x, y));
        gameField.setFieldValue(x, y, value);
        gameField.printField();
    }

}
