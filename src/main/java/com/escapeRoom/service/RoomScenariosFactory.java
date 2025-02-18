package com.escapeRoom.service;

import com.escapeRoom.entity.*;
import com.escapeRoom.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RoomScenariosFactory {
    private static final int SCENARIOS_COUNT = 3;
    private ItemRepository itemRepository;

    public RoomScenariosFactory(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addRandomScenario(Scene scene) {
        Random random = new Random();
        int randomScenario = random.nextInt(SCENARIOS_COUNT) + 1;
        List<Item> newItems = new ArrayList<>();
        switch (randomScenario){
            case 1:
                newItems = createScenario1(scene);
                break;
            case 2:
                newItems = createScenario2(scene);
                break;
            case 3:
                newItems = createScenario3(scene);
                break;
        }
        scene.addItems(newItems);
    }

    private List<Item> createScenario1(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);
        newItems.add(new Window());
        scene.lockRandomDoor(key);
        newItems.add(new Container(key,"Skrzynia"));
        return newItems;
    }

    private List<Item> createScenario2(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        newItems.add(new Window());
        return newItems;
    }
    private List<Item>createScenario3(Scene scene){
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);
        CodeForProtectContainer c = new CodeForProtectContainer();
        itemRepository.save(c);
        Picture picture = new Picture(c);
        newItems.add(picture);
        scene.lockRandomDoor(key);
        ProtectContainer protectContainer = new ProtectContainer("sefj",key,c);
        newItems.add(protectContainer);
        return newItems;
    }


}
