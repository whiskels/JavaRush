package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K, V extends Convertable<K>> Map convert(List<V> list) {
        Map<K,V> result = new HashMap<K,V>();
        for (V value : list) {
            result.put(value.getKey(), value);
        }
        return result;
    }
}
