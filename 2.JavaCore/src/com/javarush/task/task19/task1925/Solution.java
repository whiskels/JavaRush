package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));

        ArrayList<String> words = new ArrayList<String>();
        String line;

        while ((line = br.readLine()) != null) {
            String[] content = line.split(" ");
            for (int i = 0; i < content.length; i++) {
                if (content[i].length() > 6) {
                    words.add(content[i]);
                }
            }
        }
        br.close();

        String result = "";
        for (int i = 0; i < words.size()-1; i++) {
            result += words.get(i) + ",";
        }
        result += words.get(words.size()-1);

        bw.write(result);
        bw.close();
    }
}
