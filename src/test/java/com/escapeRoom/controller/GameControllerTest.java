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

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @BeforeEach
    void setup() {
        SceneDto sceneDto = new SceneDto(1, "Active Scene", "image.png", List.of());
        when(gameService.getItemsByActiveScene(1)).thenReturn(sceneDto);
    }

    @Test
    void testGetActiveScene() throws Exception {
        mockMvc.perform(get("/api/v1/games/1/active-scene"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Active Scene"));  // Zakładając, że w odpowiedzi zwracasz nazwę sceny


        verify(gameService, times(1)).getItemsByActiveScene(1);
    }

    @Test
    void testGetConnections() throws Exception {
        List<ConnectionDTO> connections = List.of(new ConnectionDTO(1, 2));

        when(gameService.getConnections(1)).thenReturn(connections);


        mockMvc.perform(get("/api/v1/games/1/connections"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].source").value(1))
                .andExpect(jsonPath("$[0].destination").value(2));

        verify(gameService, times(1)).getConnections(1);
    }

    @Test
    void testGetGame() throws Exception {
        GameDto gameDto = new GameDto();
        gameDto.setId(1);

        when(gameService.getGameDto(1)).thenReturn(gameDto);

        mockMvc.perform(get("/api/v1/games/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(gameService, times(1)).getGameDto(1);
    }

    @Test
    void testPlayerDto() throws Exception {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(1);
        playerDto.setName("Test Player");

        when(gameService.findPlayerByGameID(1)).thenReturn(playerDto);

        mockMvc.perform(get("/api/v1/games/1/player"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Player"));

        verify(gameService, times(1)).findPlayerByGameID(1);
    }
}