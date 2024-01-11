package com.escapeRoom.controller;

import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {


    private GameService gameService;

    public RoomController(GameService gameService) {
        this.gameService = gameService;
    }
}
