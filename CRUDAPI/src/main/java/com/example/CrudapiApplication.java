package com.example;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.example.check.GsonCustom;

import com.example.config.YamlConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@SpringBootApplication
public class CrudapiApplication {


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        SpringApplication.run(CrudapiApplication.class, args);


    }
}






