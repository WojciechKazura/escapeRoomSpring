package com.escapeRoom.service;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.entity.Item;
import com.escapeRoom.entity.Window;
import com.escapeRoom.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
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
        return new ArrayList<>(List.of(new Window()));
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

    @Transactional
    public String useItem(Integer id) {
        Item item = findItem(id);
        String result = item.use();
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




