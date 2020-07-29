package com.javarush.task.task27.task2712.statistic;


import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private static StatisticManager manager;
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticManager getInstance() {
        if (manager == null) {
            manager = new StatisticManager();
        }
        return manager;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private StatisticManager() {
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            storage = new HashMap<>();
            storage.put(EventType.COOKED_ORDER, new ArrayList<>());
            storage.put(EventType.NO_AVAILABLE_VIDEO, new ArrayList<>());
            storage.put(EventType.SELECTED_VIDEOS, new ArrayList<>());
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }
    }
}
