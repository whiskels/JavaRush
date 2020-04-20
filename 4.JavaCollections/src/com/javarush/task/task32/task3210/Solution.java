package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;


public class Solution {
    public static void main(String... args) throws Exception {
        if (args.length == 3) {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            raf.seek(Integer.parseInt(args[1]));
            byte[] data = new byte[args[2].length()];
            raf.read(data,0,data.length);
            String value = new String(data);
            raf.seek(raf.length());
            if (value.equals(args[2])) {
                raf.write("true".getBytes());
            }
            else {
                raf.write("false".getBytes());
            }
            raf.close();
        }

    }
}
