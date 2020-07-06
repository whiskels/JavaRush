package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        final String currency = ConsoleHelper.askCurrencyCode();
        final String[] values = ConsoleHelper.getValidTwoDigits(currency);
        final CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        final int nominal = Integer.parseInt(values[0]);
        final int amount = Integer.parseInt(values[1]);
        cm.addAmount(nominal, amount);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), nominal * amount, currency));
    }
}
