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
        return itemService.getItems();
    }

    @GetMapping
    Item getItem(Item item) {
        List<Item> itemList = getAllItems();
        for (Item item1 : itemList){
            if(item.equals(item1)){
               return item1;
            }
        }
       return null;
    }

    @PostMapping
    void addItem(@RequestBody Item item) {
        itemService.save(item);
    }


}
