package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (int i = 0; i < annotation.fullyQualifiedNames().length; i++) {
                System.out.println(annotation.fullyQualifiedNames()[i]);
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean printValues(Class c) {
        if (c.isAnnotationPresent(PrepareMyTest.class)) {
            PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for (int i = 0; i < annotation.value().length; i++) {
                System.out.println(annotation.value()[i].getSimpleName());
            }
            return true;
        } else {
            return false;
        }
    }
}

