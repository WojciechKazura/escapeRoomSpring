package com.escapeRoom.service;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.dto.PlayerDto;
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
        itemList.add(new Desk(new Coin()));
        itemList.add(new Bag(key));
        itemList.add(new Window());
        //itemList.add(key);
        itemList.add(new Door(key));
        Room room = new Room("pierwszy", "obraz1", itemList);
        player.setRoom(room);
        return playerRepository.save(player);
    }

    public void save(ItemDto item) {
        itemRepository.save(mapToItem(item));
    }

    public List<ItemDto> getAllItems() {
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<Item> itemList = itemRepository.findAll();
        for (Item item : itemList) {
            itemDtoList.add(mapToItemDto(item));
        }
        return itemDtoList;
    }

    // @Transactional
    public String doAction(ActionDto actionDto) {
        Player player = playerRepository.findById(actionDto.getPlayerId()).orElseThrow();
        Context context = new Context(player.getRoom(), player);
        Item item = findItem(actionDto.getItemId());
        if (player.getRoom().getItemList().contains(item)) {
            return item.use(context);
        }
        return "Brak przedmiotu w pokoju.";
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

    private Item findItem(int itemEntityId) {
        //todo
        return itemRepository.findById(itemEntityId).orElseThrow();
    }

    public List<ItemDto> getItemsByPlayerId(int playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow();
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<Item> itemList = player.getRoom().getItemList();
        for (Item item : itemList) {
            itemDtoList.add(mapToItemDto(item));
        }
        return itemDtoList;
    }


}
