package com.escapeRoom.service;

import com.escapeRoom.dto.AccountDto;
import com.escapeRoom.entity.Account;
import com.escapeRoom.entity.Game;
import com.escapeRoom.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AccountService implements UserDetailsService {
    private AccountRepository accountRepository;
    private GameService gameService;
    private PasswordEncoder encoder;

    public AccountService(AccountRepository accountRepository, GameService gameService, PasswordEncoder encoder) {
        this.accountRepository = accountRepository;
        this.gameService = gameService;
        this.encoder = encoder;
    }

    public AccountDto createAccount(AccountDto accountDto) {
        if (accountRepository.existsByLogin(accountDto.getLogin())) {
            throw new IllegalArgumentException("Login in use");
        }
        Game game = gameService.createGame();
        Account account = new Account(accountDto.getLogin(),encoder.encode(accountDto.getPassword()), game);
        accountRepository.save(account);
        AccountDto finalAccountDto = new AccountDto(account.getId(), account.getLogin(), account.getPassword(), game.getId());
        gameService.addScenarios(game);
        return finalAccountDto;
    }

    public AccountDto loginAccount(String userName) {
        Account account = accountRepository.findByLogin(userName).orElseThrow();
        AccountDto finalAccountDto = new AccountDto(account.getId(), account.getLogin(), account.getPassword(), account.getGame().getId());
        return finalAccountDto;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByLogin(login);
        Account account = accountOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(account.getLogin())
                .password(account.getPassword())
                .build();
    }
}
