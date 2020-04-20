package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (true) {
            try {
                s = r.readLine();
                try {
                    FileInputStream is = new FileInputStream(s);
                    is.close();
                    }
                catch (FileNotFoundException e) {
                    System.out.println(s);
                    r.close();
                    break;
                    }
            }
            catch (IOException e) {
                break;
            }
        }
    }
}
