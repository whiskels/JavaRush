package com.javarush.task.task22.task2202;

import java.lang.ref.PhantomReference;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        String result = "";
        try {
           String[] words = string.split("\\s+");
           result = String.format("%s %s %s %s", words[1], words[2], words[3], words[4]);
        } catch (Exception e) {
               throw new TooShortStringException();
        }
        return result.trim();
    }

    public static class TooShortStringException extends RuntimeException {
        public TooShortStringException() {
        }
    }
}
