package com.tictactoe.example;

import com.tictactoe.example.game.model.GameModel;

public class Main {
    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        gameModel.startGame();
    }
}