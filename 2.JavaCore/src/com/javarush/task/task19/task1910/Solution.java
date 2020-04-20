package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(r.readLine()));
        BufferedWriter fw = new BufferedWriter(new FileWriter(r.readLine()));
        r.close();


        while (fr.ready()) {
            fw.write(fr.readLine().replaceAll("\\p{P}", ""));
        }

        fr.close();
        fw.close();
    }
}