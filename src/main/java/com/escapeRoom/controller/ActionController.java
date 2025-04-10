package com.escapeRoom.controller;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.dto.ActionResultDto;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/actions")
//@CrossOrigin()
public class ActionController {

    private GameService gameService;

    public ActionController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    ActionResultDto sendAction(@RequestBody ActionDto actionDto) {
        return gameService.doAction(actionDto);
    }


}
