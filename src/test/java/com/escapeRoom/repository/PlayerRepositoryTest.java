package com.escapeRoom.repository;

import com.escapeRoom.entity.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // Adnotacja uruchamia Spring Data JPA dla testów
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;  // Wstrzykiwanie repozytorium

    @Test
    void shouldSaveAndFindPlayerById() {
        // given
        Player player = new Player(1,"aa");

        // when
        Player savedPlayer = playerRepository.save(player);  // Zapisz gracza
        Optional<Player> foundPlayer = playerRepository.findById(savedPlayer.getId());  // Pobierz po id

        // then
        assertTrue(foundPlayer.isPresent());  // Upewnij się, że gracz został znaleziony
        assertEquals("aa", foundPlayer.get().getName());  // Sprawdź nazwisko
    }

    @Test
    void shouldReturnEmptyWhenPlayerNotFound() {
        // when
        Optional<Player> player = playerRepository.findById(999);  // Szukaj nieistniejącego gracza

        // then
        assertTrue(player.isEmpty());  // Oczekuj pustego Optional
    }
}
