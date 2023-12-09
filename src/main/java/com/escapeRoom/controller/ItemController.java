package com.escapeRoom.controller;

import com.escapeRoom.entitty.Item;
import com.escapeRoom.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping
    Item getItem(Item item) {
        return itemService.getItem(item);
    }

    @PostMapping
    void addItem(@RequestBody Item item) {
        itemService.save(item);
    }


}
