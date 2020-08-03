package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
*/
public class Solution extends Thread {
    private class SolutionUncaughtExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            if (e instanceof Error) {
                System.out.println("Нельзя дальше работать");
            } else if (e instanceof  Exception) {
                System.out.println("Надо обработать");
            } else if (e != null) {
                System.out.println("Поживем - увидим");
            }
        }
    }

    public Solution() {
        setUncaughtExceptionHandler(new SolutionUncaughtExceptionHandler());
    }

    public static void main(String[] args) {
    }
}
