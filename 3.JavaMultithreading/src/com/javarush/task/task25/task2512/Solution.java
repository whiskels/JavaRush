package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List<String> exceptionMessages = new ArrayList<>();
        exceptionMessages.add(e.toString());
        while (e.getCause() != null) {
            e = e.getCause();
            exceptionMessages.add(e.getClass().getName() + ": " + e.getLocalizedMessage());
        }

       for (int i = exceptionMessages.size() - 1; i >= 0; i--) {
           System.out.println(exceptionMessages.get(i));
       }
    }

    public static void main(String[] args) {
    }
}
