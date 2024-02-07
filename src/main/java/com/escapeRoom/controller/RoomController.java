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
    @GetMapping()
    List<ItemDto> getItems(int playerId){ //tymczasowo wysyłamy id grającego playera jako parametr zapytania (docelowo wyciagane z security)
        return gameService.getItemsByPlayerId(playerId);
    }
}

//GET: api/v1/rooms/{id}/items/

//GET: api/v1/rooms
// jesteś adminem? dostajesz wszystkie pokoje
// jesteś zwykłym graczem? dostajesz swój pokój (system sprawdza id playera i daje jego pokój)

