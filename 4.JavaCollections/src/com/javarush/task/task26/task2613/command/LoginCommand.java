package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards =
            ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));

        while (true) {
            try {
                String cardString = ConsoleHelper.readString();
                String pinString = ConsoleHelper.readString();
                if (cardString.length() != 12 || pinString.length() != 4) {
                    throw new IllegalArgumentException();
                }

                if (validCreditCards.containsKey(cardString) && validCreditCards.getString(cardString).equals(pinString)) {
                    ConsoleHelper.writeMessage(res.getString("success.format"));
                    break;
                }
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                throw new IllegalArgumentException();

            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }

        }
    }
}
