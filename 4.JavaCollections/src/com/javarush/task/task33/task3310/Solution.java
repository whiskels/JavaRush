package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(),10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<Long>();

        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> values = new HashSet<String>();

        for (Long key : keys) {
            values.add(shortener.getString(key));
        }
        return values;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testValues = new HashSet<String>();

        for (int i = 0; i < elementsNumber; i++) {
            testValues.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date startIds = new Date();
        Set<Long> ids = getIds(shortener,testValues);
        Date endIds = new Date();
        Helper.printMessage(String.valueOf(endIds.getTime()-startIds.getTime()));

        Date startStrings = new Date();
        Set<String> values = getStrings(shortener,ids);
        Date endString = new Date();
        Helper.printMessage(String.valueOf(startStrings.getTime()-endString.getTime()));

        if (values.equals(testValues)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }

    }
}
