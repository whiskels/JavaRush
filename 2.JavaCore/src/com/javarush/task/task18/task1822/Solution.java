package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        BufferedReader fr = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = fr.readLine()) != null) {
            if (line.startsWith(args[0] + " ")) {
                System.out.println(line);
                break;
            }
        }
        fr.close();
        r.close();
    }
}
