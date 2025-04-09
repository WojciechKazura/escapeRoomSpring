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

        Player savedPlayer = playerRepository.save(player);  // Zapisz gracza
        Optional<Player> foundPlayer = playerRepository.findById(savedPlayer.getId());  // Pobierz po id

        assertTrue(foundPlayer.isPresent());  // Upewnij się, że gracz został znaleziony
        assertEquals("aa", foundPlayer.get().getName());  // Sprawdź nazwisko
    }

    @Test
    void shouldReturnEmptyWhenPlayerNotFound() {
        Optional<Player> player = playerRepository.findById(999);  // Szukaj nieistniejącego gracza

        assertTrue(player.isEmpty());  // Oczekuj pustego Optional
    }
}
