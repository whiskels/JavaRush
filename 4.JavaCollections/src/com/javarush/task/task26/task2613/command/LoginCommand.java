package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards =
            ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.verifiedCards");


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Please enter card number and PIN");

        while (true) {
            try {
                String cardString = ConsoleHelper.readString();
                String pinString = ConsoleHelper.readString();
                if (cardString.length() != 12 || pinString.length() != 4) {
                    throw new IllegalArgumentException();
                }

                if (validCreditCards.containsKey(cardString) && validCreditCards.getString(cardString).equals(pinString)) {
                    ConsoleHelper.writeMessage("Authorization is successful!");
                    break;
                }
                throw new IllegalArgumentException();

            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Invalid input, please try again.");
            }

        }
    }
}
