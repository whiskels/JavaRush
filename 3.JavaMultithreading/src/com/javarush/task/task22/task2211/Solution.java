package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");

        File file = new File(args[0]);
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(args[1]);

        byte[] buffer = new byte[(int) file.length()];
        fis.read(buffer);
        fis.close();
        String s = new String(buffer, windows1251);
        buffer = s.getBytes(utf8);
        fos.write(buffer);
        fos.close();
    }
}
