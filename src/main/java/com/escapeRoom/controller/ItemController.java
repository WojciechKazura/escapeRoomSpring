package com.escapeRoom.controller;

import com.escapeRoom.entitty.Item;
import com.escapeRoom.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    List<Item> getItem() {
        Item door = new Item("door");
        Item window = new Item("window");
        Item key = new Item("key");

        List<Item> itemList = new ArrayList<>();

        itemList.add(door);
        itemList.add(window);
        itemList.add(key);

        return itemList;
    }

    @PostMapping
    void addItem(@RequestBody Item item) {
        itemService.save(item);
    }




}
