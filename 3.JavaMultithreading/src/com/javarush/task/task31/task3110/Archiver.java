package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String... args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please enter path to archive");
            String path = bf.readLine();
            ZipFileManager zipFileManager = new ZipFileManager(Paths.get(path));

            System.out.println("Please enter path to file");
            path = bf.readLine();
            zipFileManager.createZip(Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
