package com.escapeRoom.repository;

import com.escapeRoom.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void shouldSaveAndFindPlayerById() {
        Player player = new Player(1, "aa");

        Player savedPlayer = playerRepository.save(player);
        Optional<Player> foundPlayer = playerRepository.findById(savedPlayer.getId());
        assertTrue(foundPlayer.isPresent());
        assertEquals("aa", foundPlayer.get().getName());
    }

    @Test
    void shouldReturnEmptyWhenPlayerNotFound() {
        Optional<Player> player = playerRepository.findById(999);

        assertTrue(player.isEmpty());
    }
}
