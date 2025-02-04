package com.escapeRoom.service;

import com.escapeRoom.dto.*;
import com.escapeRoom.entity.*;
import com.escapeRoom.repository.GameRepository;
import com.escapeRoom.repository.ItemRepository;
import com.escapeRoom.repository.PlayerRepository;
import com.escapeRoom.repository.SceneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameService {

    private ItemRepository itemRepository;
    private PlayerRepository playerRepository;
    private SceneRepository sceneRepository;
    private GameRepository gameRepository;


    GameService(ItemRepository itemRepository, PlayerRepository playerRepository, SceneRepository sceneRepository, GameRepository gameRepository) {
        this.itemRepository = itemRepository;
        this.playerRepository = playerRepository;
        this.sceneRepository = sceneRepository;
        this.gameRepository = gameRepository;
    }


    public Game createGame() {
        Game game = new Game();
//        createRoomsList(game);
        return game;
    }

    /*public void createRoomsList(Game game) {//to do
        Scene scene1 = createRoom("pierwszy", "obraz1");
        Scene scene2 = createRoom("drugi", "obraz2");
        Scene scene3 = createRoom("trzeci", "obraz3");
        Scene scene4 = createRoom("czwarty", "obraz4");
        Scene scene5 = createRoom("piąty", "obraz5");
        //todo z mapy next scena robienie gry i klucza
        scene1.setNextScene(scene2);
        scene2.setNextScene(scene3);
        scene3.setNextScene(scene4);
        scene4.setNextScene(scene5);
        scene1.getItemList().addAll(prepareRoom1Items(scene1.getKey()));
        scene2.getItemList().addAll(prepareRoom2Items(scene2.getKey()));
        scene3.getItemList().addAll(prepareRoom3Items(scene3.getKey()));
        scene4.getItemList().addAll(prepareRoom4Items(scene4.getKey()));
        scene5.getItemList().addAll(prepareRoom5Items(scene5.getKey()));
        game.setFirstScene(scene1);
        gameRepository.save(game);
    }

    private Scene createRoom(String name, String image) {// czy to będzie ten sam klucz
        List<Item> itemList = new ArrayList<>();
        Key key = new Key();
        itemList.add(new Door(key));
        Merchant merchant = prepareMerchant();
        merchant.getItemList().add(key);
        itemList.add(merchant);
        return new Scene(name, image, itemList, key);
    }

    private List<Item> prepareRoom1Items(Key key) {
        List<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(new Container(new Coin(), "Desk"));
        itemsRoom1.add(new Window());
        itemsRoom1.add(new Container(key, "Bag"));
        return itemsRoom1;
    }

    private List<Item> prepareRoom2Items(Key key) {
        List<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(new Container(new Coin(), "Desk"));
        itemsRoom1.add(new Container(key, "Bag"));
        return itemsRoom1;
    }

    private List<Item> prepareRoom3Items(Key key) {
        List<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(new Container(new Coin(), "Desk"));
        itemsRoom1.add(new Window());
        itemsRoom1.add(new Container(key, "Bag"));
        return itemsRoom1;
    }

    private List<Item> prepareRoom4Items(Key key) {
        List<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(new Container(new Coin(), "Desk"));
        itemsRoom1.add(new Window());
        itemsRoom1.add(new Container(key, "Bag"));
        return itemsRoom1;
    }

    private List<Item> prepareRoom5Items(Key key) {
        List<Item> itemsRoom1 = new ArrayList<>();
        itemsRoom1.add(new Container(new Coin(), "Desk"));
        itemsRoom1.add(new Window());
        itemsRoom1.add(new Container(key, "Bag"));
        return itemsRoom1;
    }

    private Merchant prepareMerchant() {
        List<Item> merchantList = new ArrayList<>();
        merchantList.add(new FirstAidKit());
        return new Merchant(merchantList);
    }*/


    public void save(ItemDto item) {
        itemRepository.save(mapToItem(item));
    }

    public List<ItemDto> getAllItems() {
        return convertItemsToItemDto(itemRepository.findAll());
    }

    private List<ItemDto> convertItemsToItemDto(List<Item> items) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : items) {
            itemDtoList.add(mapToItemDto(item));
        }
        return itemDtoList;
    }

    public List<SceneDto> getAllRooms() {
        List<SceneDto> sceneDtoList = new ArrayList<>();
        List<Scene> sceneList = sceneRepository.findAll();
        for (Scene scene : sceneList) {
            sceneDtoList.add(mapToSceneDto(scene));
        }
        return sceneDtoList;
    }

    // @Transactional
    public ActionResultDto doAction(ActionDto actionDto) {
        Game game = gameRepository.findById(actionDto.getGameId()).orElseThrow();
        Context context = new Context(game);
        Item item = findItem(actionDto.getItemId());
        ActionResultDto action = new ActionResultDto(item.use(context));
        gameRepository.save(game);
        return action;
    }

    public ItemDto getItem(Integer id) {
        return mapToItemDto(itemRepository.findById(id).orElseThrow());
    }

    public GameDto getGameDto(Integer id){
        return mapToDTO(gameRepository.findById(id).orElseThrow());
    }

    private Item mapToItem(ItemDto itemDto) {
        return switch (itemDto.getItemType()) {
            case WINDOW -> new Window();
            default -> throw new IllegalStateException("Nieprawidłowy typ");
        };

    }

    private ItemDto mapToItemDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getType());
    }

    private PlayerDto mapToPlayerDto(Player player) {
        return new PlayerDto(player.getName(), player.getId(), player.getHowManyCoins(), convertItemsToItemDto(player.getItemList()));
    }

    public PlayerDto findPlayerByGameID(int gameId) {
        return mapToPlayerDto(gameRepository.findById(gameId).orElseThrow().getPlayer());
    }

    private SceneDto mapToSceneDto(Scene scene) {
        return new SceneDto(scene.getId(), scene.getName(), scene.getImage(),
                scene.getItemList().stream().map(item ->mapToItemDto(item)).toList());
    }

    private Item findItem(int itemEntityId) {
        return itemRepository.findById(itemEntityId).orElseThrow();
    }

    public SceneDto getItemsByActiveScene(int gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        return mapToSceneDto(game.getActiveScene());

    }

    public List<ConnectionDTO> getConnections(int gameId) {
        List<Scene> scenes = sceneRepository.getRooms(gameId);
        List<ConnectionDTO> connectionViews = new ArrayList<>();
        for(Scene scene: scenes){
            connectionViews.addAll(scene.getConnectionsDts());
        }
        return connectionViews;
    }

    GameDto mapToDTO(Game game) {
        List<Scene> rooms = sceneRepository.getRooms(game.getId());
        return new GameDto(game.getId(),
               new SceneDto(game.getFirstScene().getId()),
                getConnections(game.getId()),
                game.getActiveScene().getId(),
                rooms.stream().map(room -> new SceneDto(room.getId())).toList());
    }
}



