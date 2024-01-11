package com.escapeRoom.service;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.entity.*;
import com.escapeRoom.repository.ItemRepository;
import com.escapeRoom.repository.PlayerRepository;
import com.escapeRoom.repository.RoomRepository;
import jakarta.annotation.PostConstruct;
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

    public String doAction(ActionDto actionDto) {
        return useItem(actionDto.getItemId());
    }

    @PostConstruct
    private void prepareDatabase() {
        if (itemRepository.count() == 0) {
            List<Item> itemList = new ArrayList<>();
            itemList.add(new Window());
            itemList.add(new Key());
            itemList.add(new Door());
            Player player = new Player("gracz1", 0, new Room("pierwszy", "obraz1", itemList));
            playerRepository.save(player);
        }
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
    public String useItem(Integer id) {
        Item item = findItem(id);
        String result = item.use();
        // itemRepository.save(item);
        return result;
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


}
