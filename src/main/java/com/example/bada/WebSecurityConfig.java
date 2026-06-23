package com.example.bada;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/index").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/search_by_stop").permitAll()
                        .requestMatchers("/search_by_line").permitAll()
                        .requestMatchers("/found_stop").permitAll()
                        .requestMatchers("/api/stops").permitAll()
                        .requestMatchers("/admin_main").hasRole("ADMIN")
                        .requestMatchers("/user_main").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/perspectives", true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/index")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
