package com.escapeRoom.repository;

import com.escapeRoom.entity.Scene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SceneRepository extends JpaRepository<Scene, Integer> {

    @Query("from Scene s where s.game.id = :gameId")
    List<Scene> getRooms(int gameId);

}
