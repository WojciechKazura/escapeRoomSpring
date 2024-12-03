package com.escapeRoom.repository;

import com.escapeRoom.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
