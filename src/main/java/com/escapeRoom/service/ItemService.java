package com.escapeRoom.service;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.entitty.Item;
import com.escapeRoom.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    private void prepareDatabase() {
        if (itemRepository.count() == 0) {
            saveElementaryItems(prepareElementaryItems());
        }
    }

    private List<Item> prepareElementaryItems() {
        return new ArrayList<>(List.of(new Item("door"), new Item("window"), new Item("key")));
    }

    private void saveElementaryItems(List<Item> itemList) {
        itemRepository.saveAll(itemList);
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

    public ItemDto getItem(Integer id) {
        return mapToItemDto(itemRepository.findById(id).orElseThrow());
    }

    private Item mapToItem(ItemDto itemDto) {
        return new Item(itemDto.getName());
    }

    private ItemDto mapToItemDto(Item item) {
        return new ItemDto(item.getId(), item.getName());
    }

}




