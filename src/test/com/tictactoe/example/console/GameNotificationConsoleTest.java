package com.tictactoe.example.console;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameNotificationConsoleTest {
    private final static String EXPECTED_USER_INPUT = "User Input";

    @Test
    void shouldSendMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        GameNotificationConsole gameNotificationConsole = new GameNotificationConsole();
        gameNotificationConsole.sendMessage("User Input");
        String actualUserInput = outputStream.toString();
        assertEquals(EXPECTED_USER_INPUT, actualUserInput);
    }
}