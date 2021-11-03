package com.example.check;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;

public class CheckSum {

    public static void main(String[] args) {
        String data = "03452256309704450001100014211002202009291129231120000FT19310RH6P11ghffffffffff";
        String sha256hash = getSHA256Hash(data);

        System.out.println("data:" + data);
        System.out.println("sha256:" + sha256hash);

    }
        public static String getSHA256Hash(String data) {
            String result = null;

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes("UTF-8"));
                return bytesToHex(hash);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }



    public static String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash).toLowerCase();

    }


        }


