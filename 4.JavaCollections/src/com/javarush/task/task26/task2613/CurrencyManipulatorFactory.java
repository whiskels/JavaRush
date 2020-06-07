package com.javarush.task.task26.task2613;

import sun.reflect.generics.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {

        String key = map.keySet()
                .stream()
                .filter((k) -> k.toUpperCase().equals(currencyCode.toUpperCase()))
                .findFirst()
                .orElse("0");

        if (key.equals("0")) {
            CurrencyManipulator newManipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, newManipulator);
            return(newManipulator);
        } else {
            return map.get(key);
        }
    }

}
