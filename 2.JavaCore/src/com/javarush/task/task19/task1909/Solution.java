package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(r.readLine()));
        BufferedWriter fw = new BufferedWriter(new FileWriter(r.readLine()));
        r.close();

        while (fr.ready()) {
            fw.write(fr.readLine().replace(".","!") +"\n");
        }

        fr.close();
        fw.close();

    }
}
