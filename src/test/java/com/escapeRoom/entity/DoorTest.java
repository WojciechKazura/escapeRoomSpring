package com.escapeRoom.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DoorTest {

    private Door door;
    private Key key;
    private Scene targetScene;
    private Scene nextScene;
    private Context context;
    private Player player;

    @BeforeEach
    void setUp() {
        key = new Key();
        targetScene = new Scene();
        nextScene = new Scene();
        player = new Player();

        context = Mockito.mock(Context.class);
        Mockito.when(context.getPlayer()).thenReturn(player);
        Mockito.when(context.getGame()).thenReturn(new Game());
        door = new Door(targetScene);
    }

    @Test
    void testUseWithoutKey() {
        assertTrue(door.isOpen(), "Door should be open initially");

        String result = door.use(context);

        assertEquals("Otworzyłeś dzwi i przechodzisz do kolejnego pokoju", result);
        assertTrue(door.isOpen(), "Door should remain open after use without key");
    }

    @Test
    void testUseWithKey() {
        door.lock(key);
        assertFalse(door.isOpen(), "Door should be locked initially");

        player.getItemList().add(key);

        String result = door.use(context);

        assertTrue(door.isOpen(), "Door should be open after using the key");
        assertEquals("Otworzyłeś dzwi kluczem i przechodzisz do kolejnego pokoju", result);
    }

    @Test
    void testUseWhenAlreadyOpen() {
        assertTrue(door.isOpen(), "Door should be open initially");

        String result = door.use(context);

        assertTrue(door.isOpen(), "Door should remain open after use");
        assertEquals("Otworzyłeś dzwi i przechodzisz do kolejnego pokoju", result);  // Oczekiwany komunikat, mimo że drzwi nie zmieniają stanu
    }

    @Test
    void testGetNameWhenClosed() {
        door.lock(key);
        assertTrue(door.getName().contains("zamknięte"), "Door name should contain 'zamknięte' when closed");
    }


}
