package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
        public static void main(String[] args) throws IOException {
            FileInputStream fis = new FileInputStream(args[0]);
            int[] array = new int [128];
            while (fis.available()>0) array[fis.read()]++;
            fis.close();
            for (int i=1; i<array.length; i++)
                if (array[i]!=0) System.out.println(((char)i)+" "+array[i]);
        }
}

