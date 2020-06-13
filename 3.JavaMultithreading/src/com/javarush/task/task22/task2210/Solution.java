package com.javarush.task.task22.task2210;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer st = new StringTokenizer(query,delimiter);
        List<String> words = new LinkedList<String>();

        while (st.hasMoreTokens()) {
            words.add(st.nextToken());
        }

        return words.toArray(new String[0]);
    }
}
