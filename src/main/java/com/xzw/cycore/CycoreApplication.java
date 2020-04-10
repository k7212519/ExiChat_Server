package com.xzw.cycore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CycoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CycoreApplication.class, args);
    }

}
