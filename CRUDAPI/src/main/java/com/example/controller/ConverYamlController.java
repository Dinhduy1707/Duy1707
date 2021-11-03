package com.example.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("duy")
public class ConverYamlController {
    private static final Logger LOGGER = LogManager.getLogger(ConverYamlController.class);

    @Value("${banks.bankCode}")
    private List<String> bankCode;
    @Value("${banks.privateKey}")
    private List<String> privateKey;

    @RequestMapping("/hi")
    public String home() {
        File f = new File("C://Users//Dinh Duy//Music//CRUDAPI//src//main//resources//application.yml");
        LOGGER.info("Real file ");
        if (f.exists()) {
            System.out.println(" bankCode" + bankCode);
            System.out.println("privateKey" + privateKey);
            return "OK";
        } else {
            LOGGER.info("File not found ");
            System.out.println("File not found!");}


        return null;
    }
}



