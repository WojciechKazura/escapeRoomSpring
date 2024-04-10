package com.escapeRoom.controller;


import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.dto.PlayerDto;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/v1/players")
@CrossOrigin()
public class PlayerController {

    private GameService gameService;

    PlayerController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/{id}")
    private PlayerDto getPlayerById(@PathVariable Integer id){
        return gameService.findPlayerByID(id);
    }


  /*  @GetMapping("/home")
    ModelAndView getHomePage2() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<PlayerDto> playersDtoList = gameService.gateAllPlayers();
        modelAndView.addObject("playersDto", playersDtoList);
        return modelAndView;
    }*/



}
