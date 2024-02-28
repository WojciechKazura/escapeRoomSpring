package com.escapeRoom.service;

import com.escapeRoom.dto.AccountDto;
import com.escapeRoom.entity.Account;
import com.escapeRoom.entity.Player;
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
        Account account = new Account(accountDto.getEmail(), accountDto.getPassword());
        accountRepository.save(account);
        Player player = new Player(account.getId(),accountDto.getPlayerName());
        gameService.createPlayer(player);
        AccountDto finalAccountDto = new AccountDto(account.getId(), account.getEmail(), account.getPassword(), player.getName());
        return finalAccountDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
