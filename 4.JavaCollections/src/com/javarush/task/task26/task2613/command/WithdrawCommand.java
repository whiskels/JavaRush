package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        final String currency = ConsoleHelper.askCurrencyCode();
        final CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        boolean isWithdrawSuccess = false;
        do {
            try {
                ConsoleHelper.writeMessage("Input amount:");

                String input;
                int amount;
                while (true) {
                    input = ConsoleHelper.readString();
                    try {
                        amount = Integer.parseInt(input);
                        if (amount <= 0) {
                            throw new IllegalArgumentException();
                        }
                        break;
                    } catch (Exception e) {
                        ConsoleHelper.writeMessage("Error, Try again:");
                        continue;
                    }
                }

                if (!cm.isAmountAvailable(amount)) {
                    throw new NotEnoughMoneyException();
                }

                final Map<Integer, Integer> withdraw = cm.withdrawAmount(amount);
                for (Map.Entry<Integer, Integer> entry : withdraw.entrySet()) {
                    System.out.println(String.format("\t%d - %d", entry.getKey(), entry.getValue()));
                }

                isWithdrawSuccess = true;
                ConsoleHelper.writeMessage("Withdrawal successful");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Not enough money, try again");
            }
        } while (!isWithdrawSuccess);
    }
}

