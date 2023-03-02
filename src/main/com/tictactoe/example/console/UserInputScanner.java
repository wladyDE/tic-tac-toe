package com.tictactoe.example.console;

import com.tictactoe.example.console.exception.BadGameOpponentValueException;
import com.tictactoe.example.console.validation.ConsoleInputValidation;
import com.tictactoe.example.console.validation.impl.CoordinatesInputValidation;
import com.tictactoe.example.console.validation.impl.GameOpponentInputValidation;
import com.tictactoe.example.game.GameContext;
import com.tictactoe.example.game.model.GameOpponent;
import com.tictactoe.example.game.model.PlayerTurn;
import com.tictactoe.example.game.model.board.Board;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInputScanner {
    private static final String POSSIBLE_START_GAME_VARIANTS_FORMAT = "press %s to play with %s";
    private static final String POSSIBLE_START_GAME_VARIANTS_FORMAT_SHORT = "%s - %s";
    private static final String SEPARATOR = ", ";

    private final Scanner scanner;
    private final ConsoleInputValidation gameOpponentInputValidation;
    private final ConsoleInputValidation coordinatesInputValidation;

    public UserInputScanner() {
        scanner = new Scanner(System.in);
        gameOpponentInputValidation = new GameOpponentInputValidation();
        coordinatesInputValidation = new CoordinatesInputValidation();
    }

    public UserInputScanner(Scanner scanner, ConsoleInputValidation gameOpponentInputValidation,
                            ConsoleInputValidation coordinatesInputValidation) {
        this.scanner = scanner;
        this.gameOpponentInputValidation = gameOpponentInputValidation;
        this.coordinatesInputValidation = coordinatesInputValidation;
    }

    public GameOpponent getGameOpponent(GameContext gameContext) {
        GameNotificationConsole gameNotificationConsole = gameContext.getGameNotificationConsole();
        List<GameOpponent> possibleGameOpponents = gameContext.getGameOpponents();

        gameNotificationConsole.sendMessage(getChooseOpponentMessage(possibleGameOpponents));

        String userInput = scanner.nextLine();
        while (!gameOpponentInputValidation.isValidInput(userInput, gameContext)) {
            gameNotificationConsole.sendMessage(getWrongGameStartInputMessage(possibleGameOpponents));
            userInput = scanner.nextLine();
        }

        try {
            return GameOpponent.getGameOpponentFromVariant(userInput);
        } catch (BadGameOpponentValueException ex) {
            gameNotificationConsole.sendMessage(getWrongGameStartInputMessage(possibleGameOpponents));
            return getGameOpponent(gameContext);
        }
    }

    public PlayerTurn getPlayerTurn(GameContext gameContext) {
        GameNotificationConsole gameNotificationConsole = gameContext.getGameNotificationConsole();
        Board board = gameContext.getBoard();
        String xInput;
        String yInput;

        gameNotificationConsole.sendMessage("Enter the coordinates of your next Turn (X and Y)");

        while (true) {
            xInput = scanner.next();
            yInput = scanner.next();

            if (validateUserCoordinate(xInput, gameContext)
                    && validateUserCoordinate(yInput, gameContext)
                    && board.isValidCell(Integer.parseInt(xInput), Integer.parseInt(yInput))) {
                break;
            }

            gameNotificationConsole.sendMessage("Your coordinates are incorrect!");
        }

        return new PlayerTurn(Integer.parseInt(xInput), Integer.parseInt(yInput));
    }

    private boolean validateUserCoordinate(String value, GameContext gameContext) {
        return coordinatesInputValidation.isValidInput(value, gameContext);
    }

    private String getChooseOpponentMessage(List<GameOpponent> possibleOpponents) {
        String formattedOpponentVariantMessage =
                getFormattedPossibleOpponentVariants(possibleOpponents, POSSIBLE_START_GAME_VARIANTS_FORMAT);

        return String.format("Choose your game: %s", formattedOpponentVariantMessage);
    }

    private String getWrongGameStartInputMessage(List<GameOpponent> possibleOpponents) {
        String formattedOpponentVariantMessage =
                getFormattedPossibleOpponentVariants(possibleOpponents, POSSIBLE_START_GAME_VARIANTS_FORMAT_SHORT);

        return String.format("Please enter valid opponent value: %s", formattedOpponentVariantMessage);
    }

    private String getFormattedPossibleOpponentVariants(List<GameOpponent> possibleOpponents, String format) {
        return possibleOpponents.stream()
                .map(possibleOpponent -> String.format(format, possibleOpponent.getOpponentVariant(),
                        possibleOpponent.getFormattedName()))
                .collect(Collectors.joining(SEPARATOR));
    }
}
