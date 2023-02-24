import java.util.Scanner;

public class Model {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final Board BOARD = new Board();

    private final BoardState BOARD_STATE = new BoardState();

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
        BOARD.printBoard();
        while (BOARD_STATE.checkWin(BOARD.getGameBoard()) == 0) {
            makeTurn(Cell.X);
            if (BOARD_STATE.checkWin(BOARD.getGameBoard()) != 0){
                break;
            }
            System.out.println("\n Another player makes turn \n");
            makeTurn(Cell.O);
            BOARD.printBoard();
        }
        BOARD_STATE.printWin(BOARD_STATE.checkWin(BOARD.getGameBoard()));
    }

    private void playAgainstAI() {
        AI ai = new AI();
        BOARD.printBoard();
        while (BOARD_STATE.checkWin(BOARD.getGameBoard()) == 0) {
            makeTurn(Cell.X);
            if (BOARD_STATE.checkWin(BOARD.getGameBoard()) != 0){
                break;
            }
            System.out.println("\n AI makes turn \n");
            ai.makeTurn(BOARD.getGameBoard());
            BOARD.printBoard();
        }
        BOARD_STATE.printWin(BOARD_STATE.checkWin(BOARD.getGameBoard()));
    }

    private void makeTurn(Cell value) {
        int x, y;
        do {
            System.out.println("Enter the coordinates of your next Turn (X and Y)");
            x = SCANNER.nextInt();
            y = SCANNER.nextInt();
        } while (!BOARD.isValidCell(x, y));
        BOARD.setBoardValue(x, y, value);
        BOARD.printBoard();
    }

}
