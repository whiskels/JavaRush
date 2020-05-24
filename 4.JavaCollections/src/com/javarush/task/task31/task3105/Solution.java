package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0]; //путь к файлу
        File file = new File(fileName); // создаем фаил
        String zipName = args[1];  //путь к архиву
        ArrayList <x> files = new ArrayList<>(); // создали лист с содержиммым архива

        ZipInputStream Zipreader = new ZipInputStream(new FileInputStream(zipName));// читалка архива
        ZipEntry entry;
        while ((entry=Zipreader.getNextEntry())!=null){ //заполняем лист
            byte[] buffer = new byte[Zipreader.available()];
            files.add(new x(entry.getName(), entry.isDirectory(), buffer));
        }
        Zipreader.close();

        for (int i=0; i<files.size(); i++){
            System.out.println(files.get(i).name+" | "+files.get(i).path);
        }

        FileOutputStream zipFile = new FileOutputStream(zipName);// поток для записи файлов в архив
        ZipOutputStream zip = new ZipOutputStream(zipFile);//запись в архив
        zip.putNextEntry(new ZipEntry("new"+file.getName()));
        Files.copy(file.toPath(), zip);
        zip.close();
    }

    public static  class x { // создали коасс который содержить имя к файлу и и его байты
        private boolean path;
        private String name;
        private byte[] content;
        public x(String name, boolean path, byte[] content) {
            this.name = name;
            this.path = path;
            this.content = content;
        }

        public boolean isPath() {
            return path;
        }

        public String getName() {
            return name;
        }

        public byte[] getContent() {
            return content;
        }
    }
}


