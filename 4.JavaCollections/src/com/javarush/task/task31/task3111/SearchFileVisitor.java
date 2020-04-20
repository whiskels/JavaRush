package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<Path>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean first = false, second = false, third = false, fourth = false;

        if (partOfName == null || file.getFileName().toString().contains(partOfName))
            first = true;
        if (partOfContent == null || new String(content).contains(partOfContent))
            second = true;
        if (minSize == 0 || content.length >= minSize)
            third = true;
        if (maxSize == 0 || content.length <= maxSize)
            fourth = true;
        if (first && second && third && fourth)
            foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setPartOfName(String name) {
        this.partOfName = name;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
