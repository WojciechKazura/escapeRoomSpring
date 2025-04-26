package com.escapeRoom.repository;

import com.escapeRoom.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    void shouldSaveAndFindGameById() {
        Game game = new Game();
        Game savedGame = gameRepository.save(game);

        Optional<Game> foundGame = gameRepository.findById(savedGame.getId());

        assertTrue(foundGame.isPresent());
        assertEquals(savedGame.getId(), foundGame.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenGameNotFound() {
        Optional<Game> game = gameRepository.findById(999);
        assertTrue(game.isEmpty());
    }
}
