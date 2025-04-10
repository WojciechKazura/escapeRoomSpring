package com.escapeRoom.configuration;

import com.escapeRoom.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

                                //requestMatchers(AntPathRequestMatcher)
//                                .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.OPTIONS, "/api/v1/auth")).permitAll()
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/auth")).permitAll()
                                .anyRequest().authenticated()//permitALL
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(accountService)
                .build();
    }

 /*   @Bean
    public InMemoryUserDetailsManager getUserDetailsManager() {
        //tworze obiekt uzytkownika
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder.encode("aaa")) // koduje haslo
                .roles("moderator")
                .build();

        UserDetails user2 = User.withUsername("adam")
                .password(passwordEncoder.encode("bbb")) // koduje haslo
                .roles("user")
                .build();

        //tworzę obiekt zarzadzajacy uzytkownikami
        return new InMemoryUserDetailsManager(user1, user2);
    }*/
}
