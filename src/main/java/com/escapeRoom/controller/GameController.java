package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.dto.RoomDto;
import com.escapeRoom.service.GameService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/v1/games")
@CrossOrigin()
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("{id}/items")
    List<ItemDto> getItems(@PathVariable int id) {
        return gameService.getItemsByGameId(id);
    }

    /*@GetMapping("/rooms")
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("rooms");
        List<RoomDto> roomDtoList = gameService.getAllRooms();
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
