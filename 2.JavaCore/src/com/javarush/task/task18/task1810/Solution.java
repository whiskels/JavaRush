package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            try {
                String name = r.readLine();
                FileInputStream is = new FileInputStream(name);
                if (is.available() < 1000) {
                    r.close();
                    is.close();
                    throw new DownloadException();
                }
            } catch (IOException e) {}
        }
    }

    public static class DownloadException extends Exception {

    }
}
