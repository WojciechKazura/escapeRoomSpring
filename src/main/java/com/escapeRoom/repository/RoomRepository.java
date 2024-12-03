package com.escapeRoom.repository;

import com.escapeRoom.entity.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Scene, Integer> {

}
