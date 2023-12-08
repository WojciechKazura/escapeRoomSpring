package com.escapeRoom.controller;

import com.escapeRoom.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {


    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
}
