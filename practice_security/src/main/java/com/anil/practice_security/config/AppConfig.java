package com.anil.practice_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig
{
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u1= User.builder().username("anil").password(passwordEncoder().encode("1234")).roles("ADMIN").build();
        UserDetails u2= User.builder().username("shiva").password(passwordEncoder().encode("5678")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(u1,u2);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
