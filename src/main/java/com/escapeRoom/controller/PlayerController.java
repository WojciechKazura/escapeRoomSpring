package com.escapeRoom.controller;


import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {

    private GameService gameService;

    PlayerController(GameService gameService) {
        this.gameService = gameService;
    }



}
