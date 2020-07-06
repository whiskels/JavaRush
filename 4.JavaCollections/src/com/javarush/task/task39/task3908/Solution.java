package com.javarush.task.task39.task3908;

import java.nio.CharBuffer;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Возможен ли палиндром?
*/
    public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPalindromePermutation(String s) {
        return CharBuffer.wrap(s.toLowerCase()
                .replaceAll("\\s+", "")
                .toCharArray())
                .chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() % 2 != 0)
                .count() <= 1L;
    }
}
