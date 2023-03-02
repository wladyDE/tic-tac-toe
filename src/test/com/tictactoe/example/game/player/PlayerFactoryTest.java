package com.tictactoe.example.game.player;

import com.tictactoe.example.game.model.GameOpponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerFactoryTest {
    @Test
    void shouldGetExpectedAiPlayer() {
        GamePlayer actualGamePlayer = PlayerFactory.getPlayer(GameOpponent.AI);

        assertTrue(actualGamePlayer instanceof AiPlayer);
    }

    @Test
    void shouldGetExpectedPlayer() {
        GamePlayer actualGamePlayer = PlayerFactory.getPlayer(GameOpponent.PLAYER);

        assertTrue(actualGamePlayer instanceof Player);
    }


}