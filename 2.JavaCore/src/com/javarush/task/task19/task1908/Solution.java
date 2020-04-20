package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static boolean isNumber(String line) {
        try {
            Double.parseDouble(line);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(r.readLine()));
        BufferedWriter fw = new BufferedWriter(new FileWriter(r.readLine()));
        r.close();

        while (fr.ready()) {
            String[] line = fr.readLine().split(" ");
            for (int i = 0; i < line.length; i++) {
                if (isNumber(line[i])) {
                    fw.write(line[i] + " ");
                }
            }
        }

        fr.close();
        fw.close();
    }
}
