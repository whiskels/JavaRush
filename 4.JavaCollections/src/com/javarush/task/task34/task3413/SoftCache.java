package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        AnyObject object = null;
        if (softReference != null) {
        object = softReference.get();}
        return object;
    }

    public AnyObject put(Long key, AnyObject value) {
        SoftReference<AnyObject> softReference1 = cacheMap.get(key);
        AnyObject object = null;
        if (softReference1 != null) {
            object = softReference1.get();
            softReference1.clear();}


        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        return object;
    }

    public AnyObject remove(Long key) {
        SoftReference<AnyObject> softReference1 = cacheMap.get(key);
        AnyObject object = null;
        if (softReference1 != null) {
            object = softReference1.get();
            softReference1.clear();}


        SoftReference<AnyObject> softReference = cacheMap.remove(key);

        return object;
    }
}