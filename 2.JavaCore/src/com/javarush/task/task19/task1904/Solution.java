package com.javarush.task.task19.task1904;

/* 
И еще один адаптер
*/

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter (Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = fileScanner.nextLine();
            String[] args = line.split(" ");
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
            String bd = args[3] + "/" + args[4] + "/" + args[5];
            Date birth = null;
            try {
                birth = sd.parse(bd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Person(args[1],args[2],args[0],birth);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
