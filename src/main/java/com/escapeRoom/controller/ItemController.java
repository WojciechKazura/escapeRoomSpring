package com.escapeRoom.controller;

import com.escapeRoom.entitty.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @GetMapping("api/v1/items")
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

    @PostMapping("api/v1/items")
    void addItem(@RequestBody Item item) {
        System.out.println(item);
    }


}
