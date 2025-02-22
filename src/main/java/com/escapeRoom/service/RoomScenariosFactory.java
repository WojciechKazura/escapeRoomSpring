package com.escapeRoom.service;

import com.escapeRoom.entity.*;
import com.escapeRoom.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomScenariosFactory {
    private static final int SCENARIOS_COUNT = 4;
    private ItemRepository itemRepository;

    public RoomScenariosFactory(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addRandomScenario(Scene scene) {
        /*Random random = new Random();
        int randomScenario = random.nextInt(SCENARIOS_COUNT) + 1;
        List<Item> newItems = new ArrayList<>();
        switch (randomScenario) {
            case 1:
                newItems = createScenario1(scene);
                break;
            case 2:
                newItems = createScenario2(scene);
                break;
            case 3:
                newItems = createScenario3(scene);
                break;
            case 4:
                newItems = createScenario4(scene);
                break;
        }*/
        List<Item> newItems = new ArrayList<>();
        newItems = createScenario4(scene);
        scene.addItems(newItems);
    }

    private List<Item> createScenario1(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);
        newItems.add(new Window());
        scene.lockRandomDoor(key);
        newItems.add(new Container(key, "Skrzynia"));
        return newItems;
    }

    private List<Item> createScenario2(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        newItems.add(new Window());
        return newItems;
    }

    private List<Item> createScenario3(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);
        CodeForProtectContainer c = new CodeForProtectContainer();
        itemRepository.save(c);
        Picture picture = new Picture(c);
        newItems.add(picture);
        scene.lockRandomDoor(key);
        ProtectContainer protectContainer = new ProtectContainer("sefj", key, c);
        newItems.add(protectContainer);
        return newItems;
    }

    private List<Item> createScenario4(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);
        PieceOfPaper pieceOfPaper = new PieceOfPaper("Kod z szuflady biórka", "1234");
        itemRepository.save(pieceOfPaper);
        Container container = new Container(pieceOfPaper, "Szuflada");
        itemRepository.save(container);
        List<Item> lamps = prepareLampsRiddle(pieceOfPaper.getMessage());
        for (Item lamp : lamps) {
            itemRepository.save(lamp);
        }
        SmallMirror mirror = new SmallMirror("Lusteroko",1234);
        itemRepository.save(mirror);
        Desk desk = new Desk("Biurko",container, mirror);
        newItems.addAll(lamps);
        newItems.add(desk);
        Button button = new Button(lamps);
        newItems.add(button);
        scene.lockRandomDoor(key);

        return newItems;
    }



    /*private List<Item> prepareDesKTop(String code) {
        List<Item> lamps = new ArrayList<>();
        int[] codeInTab = new int[4];
        int name = 0;
        for (int i = 3; i > -1; i--) {
            codeInTab[i] = code.charAt(i);
            name++;
            Lamp lamp = new Lamp("Lampa nr. " + String.valueOf(name), codeInTab[i]);
            lamps.add(lamp);
        }
        return lamps;
    }*/

    private List<Item> prepareLampsRiddle(String code) {
        List<Item> lamps = new ArrayList<>();
        for (int i = 0; i < code.length(); i++) {
            int digit = Character.getNumericValue(code.charAt(i)); // Konwersja znaku na liczbę
            String lampName = "Lampa nr. " + (i + 1); // Numerujemy od 1
            Lamp lamp = new Lamp(lampName, digit);
            lamps.add(lamp);
        }
        return lamps;
    }




}
