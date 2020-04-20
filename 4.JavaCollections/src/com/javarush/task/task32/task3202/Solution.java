package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) {
        StringWriter sw = new StringWriter();
        try {
            if (is == null) {
                return new StringWriter();
            } else {
                while (is.available() > 0) {
                    sw.write(is.read());
                }
            }
        }
        catch (Exception e) {return sw;}
        return sw;
    }
}