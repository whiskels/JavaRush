package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String first = r.readLine();
        String second = r.readLine();
        String third = r.readLine();
        r.close();

        FileInputStream is = new FileInputStream(first);
        FileOutputStream os = new FileOutputStream(second);
        FileOutputStream os2 = new FileOutputStream(third);

        byte[] one = new byte[is.available() / 2];
        byte[] two = new byte[is.available() - one.length];

        is.read(two);
        os.write(two);
        os.close();

        is.read(one);
        os2.write(one);
        os2.close();

        is.close();
    }
}
