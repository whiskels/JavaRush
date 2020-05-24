package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
    Object i = Integer.valueOf(10);
    String str = (String) i;
    }

    public void methodThrowsNullPointerException() {
        int[] array = new int[10];
        array = null;
        int i = array.length;
    }

    public static void main(String[] args) {

    }
}
