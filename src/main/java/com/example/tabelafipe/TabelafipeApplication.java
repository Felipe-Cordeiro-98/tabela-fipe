package com.example.tabelafipe;

import com.example.tabelafipe.principal.Principal;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelafipeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TabelafipeApplication.class, args);
    }


    @Override
    public void run(String... args) throws JsonProcessingException {

        Principal.exibeMenu();

    }
}