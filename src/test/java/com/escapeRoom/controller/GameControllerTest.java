package com.escapeRoom.controller;

import com.escapeRoom.dto.*;
import com.escapeRoom.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)  // Używamy WebMvcTest do testowania kontrolera
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc jest wstrzykiwane przez Spring

    @MockBean
    private GameService gameService;  // Używamy @MockBean zamiast @Mock

    @BeforeEach
    void setup() {
        // Przygotowanie danych wejściowych i wyników przed każdym testem
        SceneDto sceneDto = new SceneDto(1, "Active Scene", "image.png", List.of());
        when(gameService.getItemsByActiveScene(1)).thenReturn(sceneDto);
    }

    @Test
    void testGetActiveScene() throws Exception {
        // Testowanie endpointu, który zwraca scenę
        mockMvc.perform(get("/api/v1/games/1/active-scene"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Active Scene"));  // Zakładając, że w odpowiedzi zwracasz nazwę sceny

        // Weryfikacja, czy metoda została wywołana
        verify(gameService, times(1)).getItemsByActiveScene(1);
    }

    @Test
    void testGetConnections() throws Exception {
        // Przygotowanie danych wejściowych i wyników
        List<ConnectionDTO> connections = List.of(new ConnectionDTO(1, 2));

        // Mockowanie metody gameService.getConnections
        when(gameService.getConnections(1)).thenReturn(connections);

        // Testowanie endpointu
        mockMvc.perform(get("/api/v1/games/1/connections"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].source").value(1))
                .andExpect(jsonPath("$[0].destination").value(2));

        // Weryfikacja wywołania metody
        verify(gameService, times(1)).getConnections(1);
    }

    @Test
    void testGetGame() throws Exception {
        // Przygotowanie danych wejściowych i wyników
        GameDto gameDto = new GameDto();
        gameDto.setId(1);

        // Mockowanie metody gameService.getGameDto
        when(gameService.getGameDto(1)).thenReturn(gameDto);

        // Testowanie endpointu
        mockMvc.perform(get("/api/v1/games/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        // Weryfikacja wywołania metody
        verify(gameService, times(1)).getGameDto(1);
    }

    @Test
    void testPlayerDto() throws Exception {
        // Przygotowanie danych wejściowych i wyników
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(1);
        playerDto.setName("Test Player");

        // Mockowanie metody gameService.findPlayerByGameID
        when(gameService.findPlayerByGameID(1)).thenReturn(playerDto);

        // Testowanie endpointu
        mockMvc.perform(get("/api/v1/games/1/player"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Player"));

        // Weryfikacja wywołania metody
        verify(gameService, times(1)).findPlayerByGameID(1);
    }
}