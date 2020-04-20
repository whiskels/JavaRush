package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {
        if (args.length == 3) {
            File file = new File(args[0]);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(Math.min(Integer.parseInt(args[1]),raf.length()));
            raf.write(args[2].getBytes());
            raf.close();
        }
    }
}
