package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class CrudapiApplication {


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
      //  CommonPool.sentinelPool = RedisConfig1.getJedisSentinelPool();
        SpringApplication.run(CrudapiApplication.class, args);


    }
}






