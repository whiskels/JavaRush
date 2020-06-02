package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        List<Character> chars = new ArrayList<Character>();
        int maxLength = 0;
        int currentLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (chars.size() == 0 || !chars.contains(s.charAt(i))) {
                chars.add(s.charAt(i));
                currentLength++;
            } else if (chars.contains(s.charAt(i))) {
                chars.clear();
                chars.add(s.charAt(i));
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
            if (i == s.length()-1 && currentLength > maxLength) {
                maxLength = currentLength;
            }
        }
        return maxLength;
    }
}
