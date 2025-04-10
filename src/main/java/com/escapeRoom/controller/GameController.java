package com.escapeRoom.controller;

import com.escapeRoom.dto.*;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/games")
//@CrossOrigin(origins = "null")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{id}/active-scene")
    SceneDto getActiveScene(@PathVariable int id) {
        System.out.println("returning active scene " + id);
        return gameService.getItemsByActiveScene(id);
    }

    @GetMapping("/{id}/connections")
    List<ConnectionDTO> getConnections(@PathVariable int id) {
        return gameService.getConnections(id);
    }

    @GetMapping("/{id}")
    GameDto getGame(@PathVariable int id) {
        return gameService.getGameDto(id);
    }

    @GetMapping("/{id}/player")
    PlayerDto playerDto(@PathVariable int id) {
        return gameService.findPlayerByGameID(id);
    }
}

