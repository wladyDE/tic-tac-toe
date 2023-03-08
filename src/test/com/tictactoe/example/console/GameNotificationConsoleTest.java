package com.tictactoe.example.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameNotificationConsoleTest {
    private final static String EXPECTED_USER_INPUT = "User Input";
    private ByteArrayOutputStream outputStream;
    private GameNotificationConsole gameNotificationConsole;

    @BeforeEach
    void setUp(){
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        gameNotificationConsole = new GameNotificationConsole();
    }

    @Test
    void shouldSendMessage() {
        gameNotificationConsole.sendMessage("User Input");
        String actualUserInput = outputStream.toString().trim();
        assertEquals(EXPECTED_USER_INPUT, actualUserInput);
    }

    @Test
    void shouldSendMessageWithLineBreak() {
        gameNotificationConsole.sendMessage("User Input", false);
        String actualUserInput = outputStream.toString().trim();
        assertEquals(EXPECTED_USER_INPUT, actualUserInput);
    }
}