package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String line = "";
        try {
            line = bis.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String askCurrencyCode() {
        writeMessage("Please enter currency code \n[3 characters]");
        String currency;

        while (true) {
            currency = readString();
            if (currency.length() == 3) {
                break;
            }
            writeMessage("Invalid currency length. Please enter again.");
        }

        return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode)  {
        writeMessage("Input nominal and amount:");

        String[] input;
        while (true) {
            input = readString().split(" ");

            int nominal;
            int amount;
            try {
                nominal = Integer.parseInt(input[0]);
                amount = Integer.parseInt(input[1]);
            } catch (Exception e) {
                writeMessage("Error, Try again:");
                continue;
            }
            if (nominal <= 0 || amount <= 0 || input.length != 2) {
                writeMessage("Error, Try again:");
                continue;
            }
            break;
        }
        return input;
    }
}
