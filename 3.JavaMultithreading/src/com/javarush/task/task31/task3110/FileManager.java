package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileManager {
    private final Path rootPath;
    private final List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new ArrayList<>();
        collectFileList(rootPath);
    }

    private void collectFileList(Path path) throws IOException {
        if (!Files.isDirectory(rootPath.relativize(path))) {
            fileList.add(path);
        } else {
            DirectoryStream<Path> ds = Files.newDirectoryStream(path);
            Iterator<Path> iter = ds.iterator();
            while (iter.hasNext()) {
                collectFileList(iter.next());
            }
            ds.close();
        }

    }

    public List<Path> getFileList() {
        return fileList;
    }
}
