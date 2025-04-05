package com.escapeRoom.service;

import com.escapeRoom.dto.GameDto;
import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.dto.ActionResultDto;
import com.escapeRoom.entity.*;
import com.escapeRoom.repository.ItemRepository;
import com.escapeRoom.repository.GameRepository;
import com.escapeRoom.repository.PlayerRepository;
import com.escapeRoom.repository.SceneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private SceneRepository sceneRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private RoomScenariosFactory roomScenariosFactory;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldCallRepositorySave() {
        // Given
        ItemDto itemDto = new ItemDto(1, "Item1", ItemType.WINDOW);

        // When
        gameService.save(itemDto);

        // Then
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void getAllItems_ShouldReturnItemDtoList() {
        // Given
        Item item1 = new Window();
        Item item2 = new Figurine(45);
        List<Item> items = List.of(item1, item2);
        when(itemRepository.findAll()).thenReturn(items);

        // When
        List<ItemDto> result = gameService.getAllItems();

        // Then
        assertEquals(2, result.size());
        assertEquals("Window", result.get(0).getName());
        assertEquals("Figurka", result.get(1).getName());
    }

    @Test
    void doAction_ShouldReturnActionResult() {
        // Given
        int gameId = 1;
        int itemId = 1;
        ActionDto actionDto = new ActionDto();
        actionDto.setGameId(1);
        actionDto.setItemId(1);
        Game game = new Game();
        Item item = new Window();
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // When
        ActionResultDto result = gameService.doAction(actionDto);

        // Then
        assertNotNull(result);
        assertEquals("Okno otwarte", result.getText()); // Zakładając, że metoda `use` w Item zwraca ten obiekt.///////////////////////////
    }

    @Test
    void getItem_ShouldReturnItemDto() {
        // Given
        int itemId = 1;
        Item item = new Window();
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // When
        ItemDto result = gameService.getItem(itemId);

        // Then
        assertNotNull(result);
        assertEquals("Window", result.getName());
    }

    @Test
    void getGameDto_ShouldReturnGameDto() {
        // Given
        int gameId = 1;
        Game game = new Game();
        Scene firstScene = new Scene();
        game.setFirstScene(firstScene);
        when(gameRepository.findById(gameId)).thenReturn(Optional.of(game));

        // When
        GameDto result = gameService.getGameDto(gameId);

        // Then
        assertNotNull(result);
        assertNotNull(result.getFirstRoom());
    }

    @Test
    void addScenarios_ShouldAddScenariosToRooms() {
        // Given
        Game game = new Game();
        Scene room = new Scene();
        List<Scene> rooms = new ArrayList<>();
        rooms.add(room);
        when(sceneRepository.getRooms(game.getId())).thenReturn(rooms);

        // When
        gameService.addScenarios(game);

        // Then
        verify(roomScenariosFactory, times(1)).addRandomScenario(room);
        verify(sceneRepository, times(1)).saveAll(rooms);
    }
}
