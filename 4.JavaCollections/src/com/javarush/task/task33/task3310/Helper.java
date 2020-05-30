package com.javarush.task.task33.task3310;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class Helper {
    private static final String nums = "0123456789";
    private static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String generateRandomString() {
        return new BigInteger(130, new SecureRandom()).toString(36);
    }
}