package com.escapeRoom.controller;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.dto.ActionResultDto;
import com.escapeRoom.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ActionControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private ActionController actionController;

    @Test
    void testSendAction() {

        ActionDto actionDto = new ActionDto();
        actionDto.setItemId(1);
        actionDto.setGameId(100);
        ActionResultDto expectedResponse = new ActionResultDto("Success");

        when(gameService.doAction(actionDto)).thenReturn(expectedResponse);
        ActionResultDto actualActionResult = actionController.sendAction(actionDto);
        assertNotNull(actualActionResult);
        assertEquals("Success", actualActionResult.getText());
        verify(gameService, times(1)).doAction(actionDto);
    }
}
