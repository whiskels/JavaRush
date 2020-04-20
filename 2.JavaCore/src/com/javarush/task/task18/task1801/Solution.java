package com.javarush.task.task18.task1801;

/* 
Максимальный байт
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file = r.readLine();
        r.close();
        FileInputStream is = new FileInputStream(file);
        int num = 0;
        while(is.available() > 0) {
            int check = is.read();
            if (check > num) {
                num = check;
            }
        }
        is.close();
        System.out.println(num);
    }
}
