package com.escapeRoom.controller;
import com.escapeRoom.dto.AccountDto;
import com.escapeRoom.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin()
public class AccountController {

    private AccountService accountService;

    AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping
    public AccountDto createPlayer(@RequestBody AccountDto accountDto){
        AccountDto accountDto1= accountService.createAccount(accountDto);
        System.out.println(accountDto1);
        return accountDto1;

    }


}
