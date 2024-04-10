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


    public Player createPlayer(Player player) {
        List<Item> itemList = new ArrayList<>();
        Key key = new Key();
        itemList.add(new Container(new Coin(),"Desk"));
        itemList.add(new Container(key,"Bag"));
        itemList.add(new Window());
        itemList.add(new Key());
        //itemList.add(key);
        itemList.add(new Door(key));
        List<Item> merchantList= new ArrayList<>();
        merchantList.add(key);
        itemList.add(new Merchant(merchantList));
        Room room = new Room( "pierwszy", "obraz1", itemList);
        player.setRoom(room);
        return playerRepository.save(player);
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
        Context context = new Context(player.getRoom(), player);
        Item item = findItem(actionDto.getItemId());
        if (player.getRoom().getItemList().contains(item)) {
            return new  ActionResultDto(item.use(context));
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
        return new PlayerDto(player.getName(),player.getId(),player.getHowManyCoins(),convertItemsToItemDto(player.getItemList()));
    }

    public PlayerDto findPlayerByID(int playerId){
       return mapToPlayerDto(playerRepository.findById(playerId).orElseThrow());
    }

    private RoomDto mapToRoomDto(Room room) {
        return new RoomDto(room.getId(),room.getName(),room.getImage(),room.getItemList());
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
