package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader(args[0]));

        LinkedHashMap<String, Double> salaryMap = new LinkedHashMap<String,Double>();
        String line;
        while ((line = r.readLine()) != null) {
            String[] content = line.split(" ");
            if (salaryMap.containsKey(content[0])) {
                double add = salaryMap.get(content[0]) + Double.parseDouble(content[1]);
                salaryMap.put(content[0], add);
            } else {
                salaryMap.put(content[0], Double.parseDouble(content[1]));
            }
        }
        r.close();
        ArrayList<String> names = new ArrayList<>(salaryMap.keySet());
        Collections.sort(names);

        Map.Entry<String,Double> maxEntry = null;
        for (Map.Entry<String,Double> entry : salaryMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        for (String name : names) {
            if (salaryMap.get(name).equals(maxEntry.getValue()))
            System.out.println(name);
        }
    }
}