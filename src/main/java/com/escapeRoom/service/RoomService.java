package com.escapeRoom.service;


import com.escapeRoom.entity.*;
import com.escapeRoom.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    private Room room;

    RoomService() {
        List<Item> itemList= new ArrayList<>();
        itemList.add(new Window());
        itemList.add(new Key());
        itemList.add(new Door());
        this.room = new Room("pierwszy","obraz1",itemList);
    }


}
