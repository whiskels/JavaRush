package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String f1 = r.readLine();
        String f2 = r.readLine();
        r.close();

        FileInputStream is = new FileInputStream(f1);
        ArrayList<Integer> buffer1 = new ArrayList<Integer>();
        while (is.available() > 0) {
            buffer1.add(is.read());
        }


        FileOutputStream os = new FileOutputStream(f1);
        FileInputStream is2 = new FileInputStream(f2);
        while (is2.available() > 0) {
            int data = is2.read();
            os.write(data);
        }
        for (int i = 0; i < buffer1.size(); i++) {
            os.write(buffer1.get(i));
        }
        is.close();
        os.close();
        is2.close();


    }
}
