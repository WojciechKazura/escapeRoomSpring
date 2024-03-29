package com.escapeRoom.controller;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/actions")
public class ActionController {

    private GameService gameService;

    public ActionController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    String sendAction(@RequestBody ActionDto actionDto) {
        return gameService.doAction(actionDto);
    }


}
