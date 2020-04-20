package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String result = outputStream.toString();
        System.setOut(consoleStream);
        String[] arr = result.split(" ");
        BigInteger sum = BigInteger.valueOf(0);
        BigInteger numOne = BigInteger.valueOf(Long.parseLong(arr[0]));
        BigInteger numTwo = BigInteger.valueOf(Long.parseLong(arr[2]));

        if (arr[1].equals("+")) {
            sum = numOne.add(numTwo);
        } else if (arr[1].equals("-")) {
            sum = numOne.subtract(numTwo);
        } else if (arr[1].equals("*")) {
            sum = numOne.multiply(numTwo);
        }
        System.out.println(String.format("%s %s %s = %d", arr[0], arr[1], arr[2], sum));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

