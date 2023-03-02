package com.tictactoe.example.console;

public class GameNotificationConsole {
    public void sendMessage(final String value) {
        sendMessage(value, true);
    }

    public void sendMessage(final String value, boolean lineBreak) {
        if (lineBreak) {
            System.out.println(value);
        } else {
            System.out.print(value);
        }
    }
}
