package com.escapeRoom.repository;

import com.escapeRoom.entitty.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
