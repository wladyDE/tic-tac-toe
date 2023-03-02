package com.tictactoe.example.game.player;

import com.tictactoe.example.game.model.AI;
import com.tictactoe.example.game.model.GameOpponent;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {

    private static final Map<GameOpponent, GamePlayer> GAME_OPPONENT_GAME_PLAYER_MAP = new HashMap<>();

    static {
        GAME_OPPONENT_GAME_PLAYER_MAP.put(GameOpponent.AI, new AiPlayer());
        GAME_OPPONENT_GAME_PLAYER_MAP.put(GameOpponent.PLAYER, new Player());
    }

    public static GamePlayer getPlayer(GameOpponent gameOpponent) {
        return GAME_OPPONENT_GAME_PLAYER_MAP.getOrDefault(gameOpponent, new AiPlayer());
    }
}
