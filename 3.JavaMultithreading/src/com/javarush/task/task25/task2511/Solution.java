package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected final Thread.UncaughtExceptionHandler handler;
    protected TimerTask original;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                StringBuilder newStr0 = new StringBuilder();
                for (int i = 0; i < t.getName().length(); i++) {
                    newStr0 = newStr0.append("*");
                }
                String out = e.getMessage();
                System.out.println(out.replaceAll(t.getName(), newStr0.toString()));
            }
        };
    }

    public static void main(String[] args) {
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }
}