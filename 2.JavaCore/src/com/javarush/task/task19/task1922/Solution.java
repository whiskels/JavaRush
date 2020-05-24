package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();

        BufferedReader fr = new BufferedReader(new FileReader(fileName));
        String line = null;
        while ((line = fr.readLine()) != null) {
            String[] content = line.split(" ");
            int counter = 0;
            for (int i = 0; i < content.length; i++) {
                for (String word : words) {
                    if (content[i].equals(word)) {
                        counter++;
                    }
                }
            }
            if (counter == 2) {
                System.out.println(line);
            }
        }
        fr.close();
    }
}
