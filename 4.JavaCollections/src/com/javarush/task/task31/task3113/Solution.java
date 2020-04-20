package com.javarush.task.task31.task3113;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(r.readLine());
        r.close();


        if (!(Files.isDirectory(path))) {
            System.out.println(path.toString() + " - не папка");
            return;
        }
        else {
            SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
            Files.walkFileTree(path, searchFileVisitor);
            System.out.println("Всего папок - " + searchFileVisitor.getFoldersCount());
            System.out.println("Всего файлов - " + searchFileVisitor.getFilesCount());
            System.out.println("Общий размер - " + searchFileVisitor.getSize());
        }
    }
}
