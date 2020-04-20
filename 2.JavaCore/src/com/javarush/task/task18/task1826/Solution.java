package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream is = new FileInputStream(args[1]);
        FileOutputStream os = new FileOutputStream(args[2]);

        if (args[0].equals("-e")) {
            while (is.available() > 0) {
                int data = is.read();
                os.write(data+1);
            }
        }
        else if (args[0].equals("-d")) {
            while (is.available() > 0) {
                    int data = is.read();
                    os.write(data-1);
                }
        }
        is.close();
        os.close();
    }
}
