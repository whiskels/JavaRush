package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream(args[0]);
        int all = 0;
        int count = 0;

        while (is.available() > 0) {
            all++;
            if (Character.isWhitespace((char)is.read())) {
                count++;
            }
        }
        is.close();
        double result = (double)count/all*100;
        System.out.printf("%.2f", result);

    }
}
