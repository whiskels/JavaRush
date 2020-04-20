package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        ArrayList<String> files = new ArrayList<String>();
        while (!(fileName = r.readLine()).equals("end")) {
            files.add(fileName);
        }
        Collections.sort(files);

        String[] fileSequence = files.get(0).split(".part");
        String outputFileName = fileSequence[0];
        FileOutputStream os = new FileOutputStream(outputFileName);

        for (String file : files) {
            FileInputStream is = new FileInputStream(file);
            byte buffer[] = new byte[is.available()];
            while (is.available() > 0) {
                int count = is.read(buffer);
                os.write(buffer, 0, count);
            }
                is.close();
            }

        os.close();
        }
    }


