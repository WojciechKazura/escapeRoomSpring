package com.escapeRoom.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContextTest {

    @Test
    void testGetters() {

        Scene scene = new Scene();
        Player player = new Player();
        Game game = new Game();
        game.setFirstScene(scene);   // zakładamy, że activeScene = firstScene
        game.setPlayer(player);

        Context context = new Context(game);

        assertEquals(game, context.getGame(), "Should return the correct game");
        assertEquals(scene, context.getRoom(), "Should return the active scene");
        assertEquals(player, context.getPlayer(), "Should return the player from the game");
    }
}
