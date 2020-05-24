package com.javarush.task.task18.task1827;

/*
Прайсы
*/


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> maxNumbers = new ArrayList<>(); // для внесения id из файла
        String sB = "";
        int InMax = 0;
        String StrMax = null;
        if (args.length > 0 && args[0].equals("-c")) {
            String fullName = "";
            String id = "";
            String prodName = String.format("%-30.30s",args[1]);
            String price = String.format("%-8.8s", args[2]);
            String quantity = String.format("%-4.4s", args[3]);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String nameOfFile = reader.readLine();
                try (BufferedReader fileReader = new BufferedReader(new FileReader(nameOfFile));
                     BufferedWriter bf = new BufferedWriter(new FileWriter(nameOfFile, true))) {
                    String str = null;
                    while ((str = fileReader.readLine()) != null) {
                        sB = str.substring(0, 8);
                        sB = sB.trim();
                        maxNumbers.add(Integer.parseInt(sB));
                    }
                    InMax = Collections.max(maxNumbers);
                    StrMax = String.valueOf(++InMax);   // получаем максимальный индекс
                    id = String.format("%-8.8s", StrMax);
                    fullName = id + prodName + price + quantity;
                    bf.newLine();
                    bf.write(fullName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}