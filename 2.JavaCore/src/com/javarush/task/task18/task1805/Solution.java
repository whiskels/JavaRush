package com.javarush.task.task18.task1805;

/* 
Сортировка байт
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();
        FileInputStream is = new FileInputStream(fileName);
        LinkedList<Integer> list = new LinkedList<>();
        while (is.available() > 0) {
         list.add(is.read());
        }
        is.close();
        Collections.sort(list);
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>(list);
        for (int i : set) {
            System.out.print(i + " ");
        }
    }
}
