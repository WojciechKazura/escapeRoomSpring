package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private GameService gameService;

    public ItemController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    List<ItemDto> getAllItems() {
        return gameService.getAllItems();
    }

    @GetMapping("/{id}")
//todo zabezpieczyc przed wyjątkiem gdy mamy złe id
    ItemDto getItem(@PathVariable Integer id) {
        return gameService.getItem(id);
    }

    @PostMapping
    void addItem(@RequestBody ItemDto item) {
        gameService.save(item);
    }


}
