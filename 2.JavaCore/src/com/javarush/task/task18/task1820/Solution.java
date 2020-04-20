package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String fileName1 = scanner.nextLine();
        String fileName2 = scanner.nextLine();
        scanner.close();

        FileReader reader = new FileReader(fileName1);
        Scanner fileScanner = new Scanner(reader);
        FileWriter writer = new FileWriter(fileName2);

        fileScanner.useLocale(Locale.ENGLISH);
        while (fileScanner.hasNextDouble()){
            int data = (int) Math.round(fileScanner.nextDouble());
            writer.write(data + " ");
        }
        reader.close();
        fileScanner.close();
        writer.close();
    }
}