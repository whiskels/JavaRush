package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String line;
        ArrayList<String> filelist = new ArrayList<String>();
        while (!(line = r.readLine()).equals("exit")) {
            filelist.add(line);
        }
        r.close();
        for (int i = 0; i < filelist.size(); i++) {
            Thread thread = new ReadThread(filelist.get(i));
            thread.start();
        }

    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        public void run() {
            ArrayList<Integer> list = new ArrayList<Integer>();
                try {
                    FileInputStream is = new FileInputStream(this.fileName);
                    while (is.available() > 0) {
                            list.add(is.read());
                    }
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            int max = 0;
            int curr = 0;
            int currKey =  0;
            Set<Integer> unique = new HashSet<Integer>(list);

            for (int key : unique) {
                curr = Collections.frequency(list, key);

                if(max < curr){
                    max = curr;
                    currKey = key;
                }
            }
            resultMap.put(this.fileName,currKey);
        }
    }
}
