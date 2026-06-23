package com.example.bada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BadaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BadaApplication.class, args);
    }

}
