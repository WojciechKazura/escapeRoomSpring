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
    private RoomScenariosFactory roomScenariosFactory;


    GameService(ItemRepository itemRepository, PlayerRepository playerRepository, SceneRepository sceneRepository, GameRepository gameRepository, RoomScenariosFactory roomScenariosFactory) {
        this.itemRepository = itemRepository;
        this.playerRepository = playerRepository;
        this.sceneRepository = sceneRepository;
        this.gameRepository = gameRepository;
        this.roomScenariosFactory = roomScenariosFactory;
    }


    public Game createGame() {
        Game game = new Game();
        return game;
    }

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

    public void addScenarios(Game game) {
        List<Scene> rooms = sceneRepository.getRooms(game.getId());
        for (Scene room : rooms) {
            roomScenariosFactory.addRandomScenario(room);
        }
        sceneRepository.saveAll(rooms);
    }
}



