package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private int filesCount = 0;
    private int foldersCount = -1;
    private int size = 0;


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        foldersCount++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            filesCount++;
            size += attrs.size();
            return FileVisitResult.CONTINUE;
        }

    public int getFilesCount() {
        return filesCount;
    }

    public int getFoldersCount() {
        return foldersCount;
    }

    public int getSize() {
        return size;
    }
}
