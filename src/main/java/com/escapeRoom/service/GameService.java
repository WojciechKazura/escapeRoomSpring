package com.escapeRoom.service;

import com.escapeRoom.dto.*;
import com.escapeRoom.entity.*;
import com.escapeRoom.repository.ItemRepository;
import com.escapeRoom.repository.PlayerRepository;
import com.escapeRoom.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameService {

    private ItemRepository itemRepository;
    private PlayerRepository playerRepository;
    private RoomRepository roomRepository;


    GameService(ItemRepository itemRepository, PlayerRepository playerRepository, RoomRepository roomRepository) {
        this.itemRepository = itemRepository;
        this.playerRepository = playerRepository;
        this.roomRepository = roomRepository;
    }


    public void createPlayer(Player player) {
        createRoomsList(player);
    }

    public void createRoomsList(Player player) {//to do
        Room room1 = createRoom("pierwszy", "obraz1");
        Room room2 = createRoom("drugi", "obraz2");
        Room room3 = createRoom("trzeci", "obraz3");
        Room room4 = createRoom("czwarty", "obraz4");
        Room room5 = createRoom("piąty", "obraz5");
        room1.setNextRoom(room2);
        room2.setNextRoom(room3);
        room3.setNextRoom(room4);
        room4.setNextRoom(room5);
        room1.getItemList().addAll(prepareRoom1Items(room1.getKey()));
        player.setRoom(room1);
        playerRepository.save(player);
    }

    private Room createRoom(String name, String image) {// czy to będzie ten sam klucz
        List<Item> itemList = new ArrayList<>();
        Key key = new Key();
        itemList.add(new Door(key));
        Merchant merchant = prepareMerchant();
        merchant.getItemList().add(key);
        itemList.add(merchant);
        return new Room(name, image, itemList, key);
    }

    private List<Item> prepareRoom1Items(Key key) {
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

    public List<RoomDto> getAllRooms() {
        List<RoomDto> roomDtoList = new ArrayList<>();
        List<Room> roomList = roomRepository.findAll();
        for (Room room : roomList) {
            roomDtoList.add(mapToRoomDto(room));
        }
        return roomDtoList;
    }

    // @Transactional
    public ActionResultDto doAction(ActionDto actionDto) {
        Player player = playerRepository.findById(actionDto.getPlayerId()).orElseThrow();
        Context context = new Context(this, player.getRoom(), player);
        Item item = findItem(actionDto.getItemId());
        if (player.getRoom().getItemList().contains(item)) {
            return new ActionResultDto(item.use(context));
        }
        return new ActionResultDto("Brak przedmiotu w pokoju.");
    }

    public ItemDto getItem(Integer id) {
        return mapToItemDto(itemRepository.findById(id).orElseThrow());
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

    public PlayerDto findPlayerByID(int playerId) {
        return mapToPlayerDto(playerRepository.findById(playerId).orElseThrow());
    }

    private RoomDto mapToRoomDto(Room room) {
        return new RoomDto(room.getId(), room.getName(), room.getImage(), room.getItemList());
    }

    private Item findItem(int itemEntityId) {
        //todo
        return itemRepository.findById(itemEntityId).orElseThrow();
    }

    public List<ItemDto> getItemsByPlayerId(int playerId) {//wrapper!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111
        Player player = playerRepository.findById(playerId).orElseThrow();//id na sztywno!!!!!!!!!!!!!!!!!!!!!111
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<Item> itemList = player.getRoom().getItemList();
        for (Item item : itemList) {
            itemDtoList.add(mapToItemDto(item));
        }
        return itemDtoList;
    }


}
