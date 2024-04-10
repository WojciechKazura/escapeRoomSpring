package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/home")//("api/v1/items")
@CrossOrigin()
public class ItemController {

    private GameService gameService;

    public ItemController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/items")
    ModelAndView getHomePage() {
        System.out.println("1234567890");
        ModelAndView modelAndView = new ModelAndView("home");
        List<ItemDto> itemList = gameService.getAllItems();
        modelAndView.addObject("itemsDto", itemList);
        for (ItemDto itemDto : itemList) {
            System.out.println("!!!!!!!!!!!!!!!!!11");
            System.out.println(itemDto);
        }
        return modelAndView;
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
