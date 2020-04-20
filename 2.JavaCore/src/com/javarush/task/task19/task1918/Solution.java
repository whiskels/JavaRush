package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
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

        Pattern pattern = Pattern.compile("<" + args[0] + ">(.+?)</" + args[0] + ">", Pattern.DOTALL);


        while (fr.ready()) {
            Matcher matcher = pattern.matcher(r.readLine());
            while (matcher.find()) {
                for (int i = 0; i <= matcher.groupCount(); i++) {
                    System.out.println(i + ":" + matcher.group(i));
                }
            }
        }
        fr.close();
    }
}
