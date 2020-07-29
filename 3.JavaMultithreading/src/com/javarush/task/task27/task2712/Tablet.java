package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import javafx.beans.InvalidationListener;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
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
        Order order = null;
        try {
            order = new Order(this);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } finally {
            if (!order.isEmpty()) {
                setChanged();
                notifyObservers(order);
                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                try {
                    advertisementManager.processVideos();
                } catch (NoVideoAvailableException e) {
                    StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime() * 60));
                    logger.log(Level.INFO, "No video is available for the order " + order.toString());
                }
            }
        }
        return order;
    }

    @Override
    public String toString() {
        return String.format("Tablet{number=%d}", number);
    }
}
