package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class CashMachine {
    public static void main(String[] args) {
        try {
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
        do {
            operation = ConsoleHelper.askOperation();
            CommandExecutor.execute(operation);
        } while (operation != Operation.EXIT);
    } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Goodbye!");
        }
    }
}
