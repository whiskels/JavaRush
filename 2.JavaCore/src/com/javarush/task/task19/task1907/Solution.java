package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(r.readLine()));
        r.close();

        Pattern pattern = Pattern.compile("\\bworld\\b+");
        int count = 0;
        while (fr.ready()) {
            String search = fr.readLine();
            Matcher matcher =  pattern.matcher(search);
            while (matcher.find()) {
                count++;
            }
        }
        fr.close();

        System.out.println(count);
    }
}

