package com.escapeRoom.service;

import com.escapeRoom.dto.AccountDto;
import com.escapeRoom.entity.Account;
import com.escapeRoom.entity.Game;
import com.escapeRoom.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService implements UserDetailsService {
    private AccountRepository accountRepository;
    private GameService gameService;

    public AccountService(AccountRepository accountRepository, GameService gameService) {
        this.accountRepository = accountRepository;
        this.gameService = gameService;
    }



    public AccountDto createAccount(AccountDto accountDto) {
        Game game = gameService.createGame();
        Account account = new Account(accountDto.getEmail(), accountDto.getPassword(), game);
        accountRepository.save(account);
        AccountDto finalAccountDto = new AccountDto(account.getId(), account.getEmail(), account.getPassword(),game.getId());
        return finalAccountDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
