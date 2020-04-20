package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file = r.readLine();
        r.close();
        FileInputStream is = new FileInputStream(file);
        List<Byte> array = new ArrayList<Byte>();
        while (is.available() > 0) {
            array.add((byte)is.read());
            }
        is.close();
        System.out.println(Collections.frequency(array,(byte)44));
        }
    }

