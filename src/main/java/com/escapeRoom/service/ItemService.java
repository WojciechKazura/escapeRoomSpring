package com.escapeRoom.service;

import com.escapeRoom.entitty.Item;
import com.escapeRoom.repository.ItemRepository;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }



    public void save(Item item) {
        itemRepository.save(item);
    }


}
