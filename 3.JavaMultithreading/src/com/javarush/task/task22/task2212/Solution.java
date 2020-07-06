package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null || telNumber.length() < 10) {
            return false;
        }

        int digitsCount = telNumber.startsWith("+") ? 12 : 10;
        int maxDelimiterCount = 2;

        for (char ch : telNumber.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                System.out.println("Not a valid number - contains letters");
                return false;
            } else if (Character.isDigit(ch)) {
                digitsCount--;
            } else if (ch == '(') {
                final int index = telNumber.indexOf(ch);
                if ((telNumber.contains("-") && index > telNumber.indexOf('-'))
                        || telNumber.lastIndexOf(ch) != index) {
                    System.out.println("Not a valid number - contains illegal brackets");
                    return false;
                }

                for (int i = 1; i < 4; i++) {
                    if (!Character.isDigit(telNumber.charAt(index + i))) {
                        System.out.println("Not a valid number - contains illegal  values inside brackets");
                        return false;
                    }
                }

                if (telNumber.charAt(index + 4) != ')'
                        || telNumber.indexOf(')') != telNumber.lastIndexOf(')')) {
                    System.out.println("Not a valid number - contains illegal brackets");
                    return false;
                }
            } else if (ch == '-') {
                maxDelimiterCount--;
            }
        }

        return digitsCount == 0 && maxDelimiterCount >= 0;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7 "));
        System.out.println(checkTelNumber("050ххх4567 "));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567 "));

    }
}
