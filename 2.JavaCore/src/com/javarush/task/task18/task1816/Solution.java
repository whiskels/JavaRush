package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws Exception{
        String fileName = args[0];
        InputStream is = new FileInputStream(fileName);
        InputStreamReader sr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(sr);
        StringBuffer sb = new StringBuffer();

        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }

        is.close();
        sr.close();
        reader.close();


        String result = sb.toString();
        Pattern p = Pattern.compile("[A-Za-z]");
        Matcher m = p.matcher(result);

        int count = 0;
        while (m.find()) {
            count++;
        }

        System.out.print(count);
    }
}
