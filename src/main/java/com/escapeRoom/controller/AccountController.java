package com.escapeRoom.controller;
import com.escapeRoom.dto.AccountDto;
import com.escapeRoom.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AccountController {

    private AccountService accountService;

    AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        System.out.println("creating account " + accountDto);
        try {
            AccountDto account = accountService.createAccount(accountDto);
            return ResponseEntity.ok(account);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @PostMapping("/login")
    public AccountDto loginAccount(Authentication authentication){
        System.out.println("logging account " + authentication);
        return accountService.loginAccount(authentication.getName());
    }


}
