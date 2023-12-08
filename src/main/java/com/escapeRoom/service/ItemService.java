package com.escapeRoom.service;

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

    void saveElementaryItems(List<Item> itemList) {
        itemRepository.saveAll(itemList);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }


}
