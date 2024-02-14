package com.escapeRoom.controller;
import com.escapeRoom.dto.AccountDto;
import com.escapeRoom.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AccountController {

    private AccountService accountService;

    AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping
    public AccountDto createPlayer(@RequestBody AccountDto accountDto){
        return accountService.createAccount(accountDto);
    }


}
