package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws Exception {
    if (args.length == 2) {
        File folder = new File(args[0]);
        File input = new File(args[1]);
        File allFilesContentFile = new File(input.getParent() + "/allFilesContent.txt");

        FileUtils.renameFile(input,allFilesContentFile);
        FileUtils.deleteFile(input);

        FileWriter fw = new FileWriter(allFilesContentFile);

        ArrayList<File> fileList = new ArrayList<File>();
        listFiles(folder,fileList);
        Collections.sort(fileList);

        for (File file : fileList) {
            FileReader fr = new FileReader(file);
            int ch;
            while ((ch = fr.read()) != -1) {
            fw.write(ch);}
            fw.write("\n");
        }

        fw.close();
    }
    }

    private static void listFiles(File path, ArrayList<File> result) {
        File[] fList = path.listFiles();
        for (File file : fList) {
            if (file.isFile() && file.length() <= 50) {
                result.add(file);
            } else if (file.isDirectory()) {
                listFiles(file, result);
            }
        }
    }
}
