package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String line = "";
        try {
            line = bis.readLine();
            if (line.toUpperCase().equals("EXIT")) {
                throw new InterruptOperationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        try {
            int operationId = Integer.parseInt(readString());
            if (operationId == 0) {
                throw new IllegalArgumentException();
            }
            return Operation.getAllowableOperationByOrdinal(operationId);
        } catch (IllegalArgumentException e) {
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currency;

        while (true) {
            currency = readString();
            if (currency.length() == 3) {
                break;
            }
            writeMessage(res.getString("invalid.data"));
        }

        return currency.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(res.getString("choose.denomination.and.count.format"));

        String[] input;
        while (true) {
            input = readString().split(" ");

            int nominal;
            int amount;
            try {
                nominal = Integer.parseInt(input[0]);
                amount = Integer.parseInt(input[1]);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            if (nominal <= 0 || amount <= 0 || input.length != 2) {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
            break;
        }
        return input;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
