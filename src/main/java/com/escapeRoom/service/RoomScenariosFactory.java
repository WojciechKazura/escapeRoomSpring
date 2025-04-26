package com.escapeRoom.service;

import com.escapeRoom.entity.*;
import com.escapeRoom.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomScenariosFactory {
    private static final int SCENARIOS_COUNT = 8;
    private ItemRepository itemRepository;

    public RoomScenariosFactory(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addRandomScenario(Scene scene) {
        Random random = new Random();
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
            case 5:
                newItems = createScenario5(scene);
                break;
            case 6:
                newItems = createScenario6(scene);
                break;
            case 7:
                newItems = createScenario7(scene);
                break;
        }
        //List<Item> newItems = new ArrayList<>();// dla testów scenaiuszy
        // newItems = createScenario7(scene);
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

        CodeGenerator codeGenerator = new CodeGenerator();
        String message = String.valueOf(codeGenerator.getCode());
        PieceOfPaper pieceOfPaper = new PieceOfPaper("Kod z szuflady biórka", message);
        itemRepository.save(pieceOfPaper);

        Container container = new Container(pieceOfPaper, "Szuflada");
        itemRepository.save(container);

        List<Item> items = prepareLampsRiddle(pieceOfPaper.getMessage());
        for (Item lamp : items) {
            itemRepository.save(lamp);
        }

        SmallMirror mirror = new SmallMirror("Lusteroko", pieceOfPaper.getMessage());
        itemRepository.save(mirror);

        Desk desk = new Desk(container, mirror);
        newItems.addAll(items);
        newItems.add(desk);

        Container containerWithKey = new Container(key, "Skrynia");
        itemRepository.save(containerWithKey);

        Button button = new Button(items, containerWithKey);
        newItems.add(button);

        scene.lockRandomDoor(key);
        return newItems;
    }

    private List<Item> createScenario5(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);

        List<Book> booksList = prepareBooksRiddle();
        Collections.shuffle(booksList);

        CodeForProtectContainer codeForProtectContainer = new CodeForProtectContainer();
        itemRepository.save(codeForProtectContainer);

        ProtectContainer protectContainer = new ProtectContainer("Sefj", key, codeForProtectContainer);
        newItems.add(protectContainer);

        CodeGenerator codeGenerator = new CodeGenerator();
        itemRepository.save(codeGenerator);

        PieceOfPaper pieceOfPaper = new PieceOfPaper("Kartka na kod z regału z ksiązkami", "Brak kodu");
        itemRepository.save(pieceOfPaper);

        Container container = new Container(pieceOfPaper, "Ukryta szuflada.");
        itemRepository.save(container);

        FurnitureForBooks furnitureForBooks = new FurnitureForBooks(booksList, container, codeGenerator);
        itemRepository.save(furnitureForBooks);

        for (Book book : booksList) {
            book.setFurnitureForBooks(furnitureForBooks);
            itemRepository.save(book);
        }

        newItems.add(furnitureForBooks);
        scene.lockRandomDoor(key);

        return newItems;
    }

    private List<Item> createScenario6(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);

        List<WeightForScale> weightForScales = prepareWeightMechanismRiddle();

        WeightMechanism weightMechanism = new WeightMechanism(key, weightForScales);
        itemRepository.save(weightMechanism);

        for (WeightForScale item : weightForScales) {
            item.setWeightMechanism(weightMechanism);
            itemRepository.save(item);
        }

        newItems.add(weightMechanism);
        return newItems;
    }

    private List<Item> createScenario7(Scene scene) {
        List<Item> newItems = new ArrayList<>();
        Key key = new Key();
        itemRepository.save(key);
        scene.lockRandomDoor(key);

        CodeForProtectContainer code = new CodeForProtectContainer();
        itemRepository.save(code);

        ProtectContainer protectContainer = new ProtectContainer("Elektroniczy sejf", key, code);
        itemRepository.save(protectContainer);
        newItems.add(protectContainer);

        List<Fuse> fuseList = prepareFuseRiddle();
        Collections.shuffle(fuseList);

        ElectricBox electricBox = new ElectricBox(fuseList);
        itemRepository.save(electricBox);

        for (Fuse fuse : fuseList) {
            fuse.setElectricBox(electricBox);
            itemRepository.save(fuse);
        }

        newItems.add(electricBox);

        FlashLight flashLight = new FlashLight(electricBox);
        itemRepository.save(flashLight);
        newItems.add(flashLight);

        List<Item> itemList = prepareMirrorRiddle();

        BlackWall blackWall = new BlackWall(code, itemList, flashLight);
        itemRepository.save(blackWall);

        for (Item item : itemList) {
            if (item instanceof Mirror mirror) {
                mirror.setBlackWall(blackWall);
            }
            itemRepository.save(item);
            newItems.add(item);
        }
        newItems.add(blackWall);
        return newItems;
    }


    private List<Item> prepareLampsRiddle(String code) {
        List<Item> lamps = new ArrayList<>();
        String reversedCode = new StringBuilder(code).reverse().toString();
        for (int i = 0; i < code.length(); i++) {
            int digit = Character.getNumericValue(reversedCode.charAt(i));
            String lampName = "Lampa nr. " + (i + 1);
            Lamp lamp = new Lamp(lampName, digit);
            lamps.add(lamp);
        }
        return lamps;
    }

    private List<Book> prepareBooksRiddle() {
        List<Book> bookList = new ArrayList<>();
        List<String> titleForBooks = new ArrayList<>();
        titleForBooks.add("a");
        titleForBooks.add("z");
        titleForBooks.add("b");
        titleForBooks.add("p");
        titleForBooks.sort(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < 4; i++) {
            Book book = new Book(titleForBooks.get(i), "a", i + 1);
            bookList.add(book);
        }
        Collections.shuffle(bookList);
        return bookList;
    }

    private List<WeightForScale> prepareWeightMechanismRiddle() {
        List<WeightForScale> itemList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            WeightForScale weightForScale = new WeightForScale(i + 1);
            itemList.add(weightForScale);
        }
        return itemList;
    }

    private List<Item> prepareMirrorRiddle() {
        List<Item> mirrorList = new ArrayList<>();
        List<Integer> correctAmgleList = List.of(90, 180, 270);
        for (int i = 0; i < 3; i++) {
            Mirror mirror = new Mirror("Lustro " + (i + 1), 0, correctAmgleList.get(i));
            mirrorList.add(mirror);
        }
        return mirrorList;
    }


    private List<Fuse> prepareFuseRiddle() {
        List<Fuse> fuseList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Fuse fuse = new Fuse("A" + (i * 10), i + 1);
            fuseList.add(fuse);
        }
        return fuseList;
    }

    private List<Item> preparePictureRiddle(Picture truPicture) {
        List<Item> picturesList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Picture picture = new Picture(new CodeForProtectContainer());
            picturesList.add(picture);
        }
        picturesList.add(truPicture);
        return picturesList;
    }

}
