package com.javarush.task.task18.task1804;

/* 
Самые редкие байты
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file = r.readLine();
        r.close();
        FileInputStream is = new FileInputStream(file);
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (is.available() > 0) {
            list.add(is.read());
        }
        is.close();
        Collections.sort(list);
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<Integer>(list);
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        for (int i : hashSet) {
            treeMap.put(i, Collections.frequency(list, i));
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        int min = max;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
            }
        }
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            if (entry.getValue() == min) {
                System.out.print(entry.getKey() + " ");
            }
        }
    }
}