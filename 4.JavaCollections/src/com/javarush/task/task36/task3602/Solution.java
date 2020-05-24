package com.javarush.task.task36.task3602;

import javax.management.relation.RoleList;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws NoSuchMethodException, ClassNotFoundException {
        Class[] classes = Collections.class.getDeclaredClasses();

        for (int i = 0; i < classes.length; i++) {
            if (List.class.isAssignableFrom(classes[i].getSuperclass())
                && (Modifier.isPrivate(classes[i].getModifiers()))
                && (Modifier.isStatic(classes[i].getModifiers())))
            {
                classes[i].getDeclaredMethod("get",int.class).setAccessible(true);
                try {
                    classes[i].getDeclaredConstructor();

                } catch (NoSuchMethodException e) {
                    return classes[i];
                }
            }
            }
        return null;
    }
}
