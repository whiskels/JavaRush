package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String[] words = new String[0];
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
             FileReader fr = new FileReader(bf.readLine()))
        {
            StringBuilder buffer = new StringBuilder();
            while (fr.ready()) {
                buffer.append((char) fr.read());
            }
            StringBuilder result = getLine(buffer.toString().split(" "));
            System.out.println(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0 || words == null) return new StringBuilder();

        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        StringBuilder result = new StringBuilder();

        // Brute forcing the validator
        while (isUnsorted(list)) {
            Collections.shuffle(list);
        }

        for (String s: list) {
            result.append(s)
                  .append(" ");
        }

        result.setLength(result.length()-1);

        return result;
    }

    public static boolean isUnsorted(ArrayList<String> words) {
        for (int i = 0; i < words.size() - 1; i++) {
            String firstWord = words.get(i).toLowerCase();
            String secondWord = words.get(i + 1).toLowerCase();
            if (firstWord.charAt(firstWord.length() - 1) != secondWord.charAt(0)) return true;
        }
        return false;
    }
}