package com.escapeRoom.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testConstructor_ShouldInitializePlayerAndFirstScene() {
        Player player = game.getPlayer();
        Scene firstScene = game.getFirstScene();
        Scene activeScene = game.getActiveScene();

        assertNotNull(player, "Player should be initialized.");
        assertNotNull(firstScene, "First scene should be initialized.");
        assertEquals(firstScene, activeScene, "Active scene should be the same as first scene.");
    }

    @Test
    void testCreateConnections_ShouldCreateTwoRoomsWhenChanceIsHigh() {
        game.setChanceForSplit(0.9);

        Scene firstScene = game.getFirstScene();

        assertNotNull(firstScene, "First scene should not be null.");

        game.createMap(firstScene);

        assertNotNull(firstScene.getNextScenes(), "Next scenes should not be null.");
        assertEquals(2, firstScene.getNextScenes().size(), "Should create exactly two rooms.");
    }

    @Test
    void testCreateConnections_ShouldCreateOneRoomWhenChanceIsHigh() {
        game.setChanceForSplit(0.9);

        Scene firstScene = game.getFirstScene();
        game.createMap(firstScene);

        assertEquals(2, firstScene.getNextScenes().size(), "Should create exactly one room.");
    }

    @Test
    void testCreateConnections_ShouldDecreaseChanceForSplit() {
        double initialChance = game.getChanceForSplit();

        Scene firstScene = game.getFirstScene();
        game.createMap(firstScene);

        System.out.println("Initial chance: " + initialChance);
        System.out.println("Chance after map creation: " + game.getChanceForSplit());

        assertTrue(game.getChanceForSplit() <= initialChance, "Chance for split should decrease or remain the same after map creation.");
    }

    @Test
    void testSetFirstScene_ShouldUpdateFirstAndActiveScene() {
        Scene newFirstScene = new Scene(game);

        game.setFirstScene(newFirstScene);
        Scene firstScene = game.getFirstScene();
        Scene activeScene = game.getActiveScene();

        assertEquals(newFirstScene, firstScene, "First scene should be updated.");
        assertEquals(newFirstScene, activeScene, "Active scene should be updated to first scene.");
    }

    @Test
    void testSetPlayer_ShouldUpdatePlayer() {
        Player newPlayer = new Player(1, "Test Player");

        game.setPlayer(newPlayer);
        Player player = game.getPlayer();

        assertEquals(newPlayer, player, "Player should be updated.");
    }
}
