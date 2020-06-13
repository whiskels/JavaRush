package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if ((telNumber == null) || (telNumber == "")) return false;

        //TODO regex
        boolean startsWithPlus = false;
        if (telNumber.charAt(0) == '+') {
            startsWithPlus = true;
        }


        return false;
    }

    public static void main(String[] args) {

    }
}
