package com.escapeRoom.repository;

import com.escapeRoom.entitty.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
