package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            BufferedReader r = new BufferedReader(new FileReader(args[0]));
            String fileContent = "";
            TreeSet<Character> set = new TreeSet<Character>();
            while (r.ready()) {
                fileContent = r.readLine().toLowerCase();
                char[] fileContentChars = fileContent.toCharArray();
                for (Character ch : fileContentChars) {
                    if (Character.isAlphabetic(ch)) {
                        set.add(ch);
                    }
                }
            }

            set.stream().limit(5).forEach(System.out::print);
        }
    }
}
