package com.escapeRoom.controller;

import com.escapeRoom.service.PlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/player")
public class PlayerContoller {

    private PlayerService playerService;

    PlayerContoller(PlayerService playerService) {
        this.playerService = playerService;
    }


}
