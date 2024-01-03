package com.escapeRoom.service;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.entity.Item;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private ItemService itemService;

    public ActionService(ItemService itemService) {
        this.itemService = itemService;
    }

    public String doAction(ActionDto actionDto) {
        return  itemService.useItem(actionDto.getItemId());
    }


}
