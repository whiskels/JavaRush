package com.javarush.task.task26.task2613;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        String currency = ConsoleHelper.askCurrencyCode();
        String[] values = ConsoleHelper.getValidTwoDigits(currency);
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        cm.addAmount(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
        ConsoleHelper.writeMessage(String.valueOf(cm.getTotalAmount()));
    }
}
