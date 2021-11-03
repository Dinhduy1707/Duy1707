package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class CrudapiApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        SpringApplication.run(CrudapiApplication.class, args);
    }
}


