package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
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
    List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")//todo zabezpieczyc przed wyjątkiem gdy mamy złe id
    ItemDto getItem(@PathVariable Integer id) {
        return itemService.getItem(id);
    }

    @PostMapping
    void addItem(@RequestBody ItemDto item) {
        itemService.save(item);
    }


}
