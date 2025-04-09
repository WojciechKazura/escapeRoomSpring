package com.escapeRoom.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyTest {

    private Key key;
    private Context context;
    private Game game;
    private Scene scene;
    private Player player;

    @BeforeEach
    void setUp() {
        game = new Game();
        player = new Player();
        scene = new Scene(game);
        context = new Context(game);
        game.setPlayer(player);
        game.setActiveScene(scene);

        key = new Key();
        scene.getItemList().add(key);
    }

    @Test
    void testConstructor() {
        assertEquals("Key", key.getName().split(" ")[0]);
        assertEquals(ItemType.KEY, key.getType());
    }

    @Test
    void testUse() {
        player.getItemList().clear();

        assertTrue(scene.getItemList().contains(key), "Key should be in the room initially");

        assertFalse(player.getItemList().contains(key), "Key should not be in the player's inventory initially");

        String result = key.use(context);

        assertEquals("Klucz zabrany z pokoju.", result);

        assertTrue(player.getItemList().contains(key), "Key should be in the player's inventory after use");

        assertFalse(scene.getItemList().contains(key), "Key should no longer be in the room after use");
    }



    @Test
    void testSetLabel() {
        key.setLabel(5);
        assertEquals("Key 5", key.getName());
    }

    @Test
    void testChanceForSplitDecreasesAfterMapCreation() {
        double initialChance = game.getChanceForSplit();

        game.createMap(game.getFirstScene());

        double finalChance = game.getChanceForSplit();

        assertTrue(finalChance < initialChance, "Chance for split should decrease after map creation.");
    }
}
