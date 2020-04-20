package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        String decode = null;
        if (reader == null) {
            decode = "no data";
        }
        else {
            StringWriter sw = new StringWriter();
            int ch;
            while ((ch = reader.read()) != -1) {
                byte data = (byte) (ch + key);
                sw.write(data);
            }
        decode = sw.toString();
            sw.close();
            reader.close();
        }
        return decode;
    }
}
