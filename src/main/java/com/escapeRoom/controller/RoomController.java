package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    private GameService gameService;

    public RoomController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/{id}/items")
    List<ItemDto> getItems(@PathVariable int id){
        return gameService.getRoomItems(id);
    }


}

//GET: api/v1/rooms/{id}/items/
