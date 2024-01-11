package com.escapeRoom.controller;

import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/player")
public class PlayerContoller {

    private GameService gameService;

    PlayerContoller(GameService gameService) {
        this.gameService = gameService;
    }


}
