package com.escapeRoom.controller;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.service.ActionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/actions")
public class ActionController {

private ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService=actionService;
    }

    @PostMapping
    String sendAction(@RequestBody ActionDto actionDto) {
        return actionService.doAction(actionDto);

    }







}
