package com.javarush.task.task18.task1809;

/* 
Реверс файла
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

        FileInputStream first = new FileInputStream(f1);
        ArrayList<Byte> file = new ArrayList<Byte>();
        while (first.available() > 0) {
            file.add((byte)first.read());
        }
        first.close();

        FileOutputStream second = new FileOutputStream(f2);
        for (int i = file.size()-1; i >= 0; i--) {
            second.write(file.get(i));
        }
        second.close();
    }
}
