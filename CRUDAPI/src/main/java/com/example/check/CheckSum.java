package com.example.check;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class CheckSum {

//    public static void main(String[] args) throws IOException {
  //     Gson gson = new Gson();
   //     Product product = new Product();

//        product.setAccountNo("0001100014211002");
//        String data = product.getMobie() + product.getAccountNo();
//
//    }

    public static String sha256Base64(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}





