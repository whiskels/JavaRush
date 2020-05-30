package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27));
    }

    public static boolean isPowerOfThree(int n) {
        return (int)(Math.log(n)/Math.log(3))==(Math.log(n)/Math.log(3));
    }
}
