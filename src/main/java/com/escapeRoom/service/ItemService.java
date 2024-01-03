package com.escapeRoom.service;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.entitty.ItemEntity;
import com.escapeRoom.entitty.ItemType;
import com.escapeRoom.model.Item;
import com.escapeRoom.model.Window;
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

    private List<ItemEntity> prepareElementaryItems() {
        return new ArrayList<>(List.of(new ItemEntity("door"), new ItemEntity("window"), new ItemEntity("key")));
    }

    private void saveElementaryItems(List<ItemEntity> itemEntityList) {
        itemRepository.saveAll(itemEntityList);
    }

    public void save(ItemDto item) {
        itemRepository.save(mapToItem(item));
    }

    public List<ItemDto> getAllItems() {
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        for (ItemEntity itemEntity : itemEntityList) {
            itemDtoList.add(mapToItemDto(itemEntity));
        }
        return itemDtoList;
    }

    public ItemDto getItem(Integer id) {
        return mapToItemDto(itemRepository.findById(id).orElseThrow());
    }

    private ItemEntity mapToItem(ItemDto itemDto) {
        return new ItemEntity(itemDto.getName());
    }

    private ItemDto mapToItemDto(ItemEntity itemEntity) {
        return new ItemDto(itemEntity.getId(), itemEntity.getName());
    }

    public Item findItem(int itemEntityId) {
        //todo
        ItemEntity itemEntity = itemRepository.findById(itemEntityId).orElseThrow();
        if (itemEntity.getType() == ItemType.WINDOW) {
            Item window = new Window(itemEntity);
            return window;
        }
        return null;
    }

}




