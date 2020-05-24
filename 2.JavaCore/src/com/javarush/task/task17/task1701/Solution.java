package com.javarush.task.task17.task1701;

import java.util.Arrays;

public class Solution {

    public static boolean comp(int[] a, int[] b) {
        System.out.println(b.length);
        if (a.length == 0 || b.length == 0) {
            if (a.length == b.length) {
                return true;}
            else {
                return false;}
        } else {
            for (int i = 0; i < a.length; i++) {
                a[i] = (int)Math.abs(a[i]);
            }

            for (int i = 0; i < b.length; i++) {
                b[i] = (int)Math.sqrt(b[i]);
            }

            Arrays.sort(a);
            Arrays.sort(b);

            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
