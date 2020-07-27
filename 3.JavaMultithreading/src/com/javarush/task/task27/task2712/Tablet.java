package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;
import javafx.beans.InvalidationListener;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Logger;

public class Tablet extends Observable {
    private static final Logger logger = Logger.getLogger(Tablet.class.getName());
    private final int number;
    private InvalidationListener cook;
    private Order order;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            order = new Order(this);
            if (!order.isEmpty()) {
                setChanged();
                notifyObservers(order);
            }
            ConsoleHelper.writeMessage(order.toString());
        } catch (IOException e) {
            logger.severe("Console is unavailable.");
            return null;
        }
        return order;
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }
}
