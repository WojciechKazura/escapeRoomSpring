package com.escapeRoom.controller;

import com.escapeRoom.dto.*;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/games")
@CrossOrigin()
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{id}/active-scene")
    SceneDto getActiveScene(@PathVariable int id) {
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
    PlayerDto playerDto (@PathVariable int id){
        return gameService.findPlayerByGameID(id);
    }

    /*@GetMapping("/rooms")
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("rooms");
        List<SceneDto> roomDtoList = gameService.getAllRooms();
        modelAndView.addObject("RoomsDto", roomDtoList);
        return modelAndView;
    }*/


}
// {id} -> @PathVariable
// z json -> @RequestBody
// z parametru (?id=10) -> @RequestParam



//GET: api/v1/rooms/{id}/items/

//GET: api/v1/rooms
// jesteś adminem? dostajesz wszystkie pokoje
// jesteś zwykłym graczem? dostajesz swój pokój (system sprawdza id playera i daje jego pokój)


/*<tr th:each="itemDto:${itemsDto} ">
                <th th:text="${itemDto.id}">
                    id
                </th>
                <th th:text="${itemDto.name}">
                    name
                </th>
                <th th:text="${itemDto.itemType}">
                    itemType
                </th>
            </tr>*/
