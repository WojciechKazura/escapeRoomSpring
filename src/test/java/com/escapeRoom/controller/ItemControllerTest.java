package com.escapeRoom.controller;

import com.escapeRoom.dto.ItemDto;
import com.escapeRoom.entity.ItemType;
import com.escapeRoom.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ItemControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private ItemController itemController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    void getAllItems_ShouldReturnItemsList() throws Exception {
        ItemDto item1 = new ItemDto(1, "Item1", ItemType.FURNITURE);
        ItemDto item2 = new ItemDto(2, "Item2", ItemType.CONTAINER);
        List<ItemDto> items = Arrays.asList(item1, item2);

        when(gameService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/home")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Item1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Item2"));

        verify(gameService, times(1)).getAllItems();
    }

    @Test
    void getItem_ShouldReturnItem() throws Exception {
        ItemDto item = new ItemDto(1, "Item1", ItemType.FURNITURE);

        when(gameService.getItem(1)).thenReturn(item);

        mockMvc.perform(get("/home/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Item1"));

        verify(gameService, times(1)).getItem(1);
    }

    @Test
    void addItem_ShouldSaveItem() throws Exception {
        ItemDto newItem = new ItemDto(0, "NewItem", ItemType.DOOR);

        mockMvc.perform(post("/home")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"NewItem\",\"itemType\":\"DOOR\"}"))
                .andExpect(status().isOk());

        ArgumentCaptor<ItemDto> argumentCaptor = ArgumentCaptor.forClass(ItemDto.class);
        verify(gameService, times(1)).save(argumentCaptor.capture());


        ItemDto capturedItem = argumentCaptor.getValue();
        assert capturedItem.getName().equals(newItem.getName());
        assert capturedItem.getItemType().equals(newItem.getItemType());
    }
}
