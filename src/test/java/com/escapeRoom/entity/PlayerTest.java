package com.escapeRoom.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(1,"TestPlayer");
    }

    @Test
    void testInitialHp_ShouldBe100() {
        assertEquals(100, player.getHp());
    }

    @Test
    void testAddItem_ShouldAddNonCoinToItemList() {
        Item key = new Key(); // zakładamy, że Key nie jest monetą
        player.addItem(key);

        assertTrue(player.getItemList().contains(key));
        assertEquals(1, player.getItemList().size());
    }

    @Test
    void testAddItem_ShouldIncrementCoins_WhenItemIsCoin() {
        int initialCoins = player.getHowManyCoins();
        Item coin = new Coin(); // Coin powinien mieć type == COIN

        player.addItem(coin);

        assertEquals(initialCoins + 1, player.getHowManyCoins());
        assertFalse(player.getItemList().contains(coin)); // moneta nie powinna być w liście itemów
    }

    @Test
    void testPay_ShouldReturnTrue_WhenEnoughCoins() {
        player.setHowManyCoins(5);

        boolean result = player.pay(3);

        assertTrue(result);
        assertEquals(2, player.getHowManyCoins());
    }

    @Test
    void testPay_ShouldReturnFalse_WhenNotEnoughCoins() {
        player.setHowManyCoins(2);

        boolean result = player.pay(5);

        assertFalse(result);
        assertEquals(2, player.getHowManyCoins());
    }

    @Test
    void testGetName_ShouldReturnCorrectName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void testSetHp_ShouldUpdateHpCorrectly() {
        player.setHp(75);
        assertEquals(75, player.getHp());
    }
}
