package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        final String currency = ConsoleHelper.askCurrencyCode();
        final CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency);
        boolean isWithdrawSuccess = false;
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        do {
            try {
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
                        ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                        continue;
                    }
                }

                if (!cm.isAmountAvailable(amount)) {
                    throw new NotEnoughMoneyException();
                }

                final Map<Integer, Integer> withdraw = cm.withdrawAmount(amount);
                for (Map.Entry<Integer, Integer> entry : withdraw.entrySet()) {
                    System.out.println(String.format(res.getString("success.format"), entry.getKey(), entry.getValue()));
                }

                isWithdrawSuccess = true;
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));

            }
        } while (!isWithdrawSuccess);
    }
}

