package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Своя реализация
*/
public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
       byte[] buffer = Files.readAllBytes(Paths.get(fileName));
        return buffer;
    }

    public static List<String> readLines(String fileName) throws IOException {
        List<String> buffer = Files.readAllLines(Paths.get(fileName));
        return buffer;
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        Files.write(Paths.get(fileName),bytes);
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        Files.copy(Paths.get(resourceFileName),Paths.get(destinationFileName));
    }
}
