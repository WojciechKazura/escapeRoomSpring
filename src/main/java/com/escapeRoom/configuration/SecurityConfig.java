package com.escapeRoom.configuration;

import com.escapeRoom.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private AccountService accountService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(AccountService userService, PasswordEncoder passwordEncoder) {
        this.accountService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(customizer -> customizer.disable())
                .headers(customizer -> customizer.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/auth/register")).permitAll()
//                                .requestMatchers("/console").permitAll()
                                .anyRequest().authenticated()//permitALL
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(accountService)
                .build();
    }

}
