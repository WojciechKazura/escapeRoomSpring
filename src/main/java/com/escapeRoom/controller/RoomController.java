package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.dto.RoomDto;
import com.escapeRoom.service.GameService;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin()
public class RoomController {

    private GameService gameService;

    public RoomController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping()
//wrpper!!!!!!!!!!!!!!!!!!!!!!//to było tutaj!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    List<ItemDto> getItems(Integer playerId) { //tymczasowo wysyłamy id grającego playera jako parametr zapytania (docelowo wyciagane z security)

        return gameService.getItemsByPlayerId(playerId);
    }

    @GetMapping("/rooms")
    ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView("rooms");
        List<RoomDto> roomDtoList = gameService.getAllRooms();
        modelAndView.addObject("RoomsDto", roomDtoList);
        return modelAndView;
    }


}

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
