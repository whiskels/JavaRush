package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/


public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {

    }

    public static class ExceptionFactory {
        public static Throwable ExceptionFactory(Enum e) {
            if (e != null) {
                String s = e.name().charAt(0) + e.name().substring(1).toLowerCase().replace("_", " ");
                if (e instanceof ApplicationExceptionMessage) {
                    return new Exception(s);
                } else if (e instanceof DatabaseExceptionMessage) {
                    return new RuntimeException(s);
                } else if (e instanceof UserExceptionMessage) {
                    try {
                        return new Error(s);
                    } catch (Exception a) {
                        return new IllegalArgumentException();
                    }
                }
                return new IllegalArgumentException();
            } else {
                return new IllegalArgumentException();
            }
        }
    }
}