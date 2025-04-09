package com.escapeRoom.entity;

import com.escapeRoom.dto.ConnectionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {

    private Scene scene;

    @BeforeEach
    void setUp() {
        scene = new Scene("Room 1", "room1.jpg", new ArrayList<>());
    }

    @Test
    void testAddItems_ShouldAddItemsToItemList() {

        Item window = new Window();
        Item mirror = new Mirror();
        List<Item> itemsToAdd = List.of(window, mirror);


        scene.addItems(itemsToAdd);


        assertEquals(2, scene.getItemList().size());
        assertTrue(scene.getItemList().contains(window));
        assertTrue(scene.getItemList().contains(mirror));
    }

    @Test
    void testLockRandomDoor_ShouldLockADoor() {

        Scene targetScene = new Scene("Room 2", "room2.jpg", new ArrayList<>());
        Door door1 = new Door();
        door1.setTargetScene(targetScene); // ustaw docelową scenę!
        Door door2 = new Door();
        door2.setTargetScene(targetScene);
        scene.addItems(List.of(door1, door2));

        Key key = new Key();


        scene.lockRandomDoor(key);


        boolean anyLocked = scene.getItemList().stream()
                .filter(i -> i instanceof Door)
                .map(i -> (Door) i)
                .anyMatch(door -> !door.isOpen());

        assertTrue(anyLocked);
    }


    @Test
    void testSetNextScenes_ShouldAddDoorsToBothScenes() {

        Scene otherScene = new Scene("Room 2", "room2.jpg", new ArrayList<>());
        List<Scene> nextScenes = List.of(otherScene);


        scene.setNextScenes(nextScenes);


        assertEquals(1, scene.getNextScenes().size());
        assertTrue(scene.getItemList().stream().anyMatch(i -> i instanceof Door));
        assertTrue(otherScene.getItemList().stream().anyMatch(i -> i instanceof Door));
    }

    @Test
    void testGetConnectionsDts_ShouldReturnConnectionDTOList() {

        Scene otherScene = new Scene("Room 2", "room2.jpg", new ArrayList<>());
        scene.setNextScenes(List.of(otherScene));
        scene.getConnectionsDts(); // ustawia sceny wzajemnie

        List<ConnectionDTO> connections = scene.getConnectionsDts();

        assertEquals(1, connections.size());
        assertEquals(scene.getId(), connections.get(0).getFrom());
        assertEquals(otherScene.getId(), connections.get(0).getTo());
    }
}
