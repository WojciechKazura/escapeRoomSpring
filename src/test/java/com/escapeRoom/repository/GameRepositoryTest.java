package com.escapeRoom.repository;

import com.escapeRoom.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")  // jeśli masz application-test.properties
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    void shouldSaveAndFindGameById() {
        // given
        Game game = new Game();  // ustaw pola, jeśli trzeba
        Game savedGame = gameRepository.save(game);

        // when
        Optional<Game> foundGame = gameRepository.findById(savedGame.getId());

        // then
        assertTrue(foundGame.isPresent());
        assertEquals(savedGame.getId(), foundGame.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenGameNotFound() {
        // when
        Optional<Game> game = gameRepository.findById(999);

        // then
        assertTrue(game.isEmpty());
    }
}
