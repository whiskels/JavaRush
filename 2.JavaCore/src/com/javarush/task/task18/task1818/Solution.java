package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String f1 = r.readLine();
        String f2 = r.readLine();
        String f3 = r.readLine();
        r.close();

        FileInputStream is2 = new FileInputStream(f2);
        FileInputStream is3 = new FileInputStream(f3);
        FileOutputStream os1 = new FileOutputStream(f1);

        while (is2.available() > 0) {
            os1.write(is2.read());
        }
        while (is3.available() > 0) {
            os1.write(is3.read());
        }

        os1.close();
        is2.close();
        is3.close();

    }
}
