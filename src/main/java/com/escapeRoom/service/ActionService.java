package com.escapeRoom.service;

import com.escapeRoom.dto.ActionDto;
import com.escapeRoom.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private ItemService itemService;

    public ActionService(ItemService itemService) {
        this.itemService = itemService;
    }

    public String doAction(ActionDto actionDto) {
        Item item = itemService.findItem(actionDto.getItemId());
        System.out.println(item.use());
        return  item.use();
    }


}
