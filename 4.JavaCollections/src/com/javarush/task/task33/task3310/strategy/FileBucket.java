package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;
import com.javarush.task.task33.task3310.Helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile("tmp", null);
            Files.deleteIfExists(path);
            Files.createFile(path);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() throws IOException {
        return Files.size(path);
    }

    public void putEntry(Entry entry) {
        try {
            OutputStream fos = Files.newOutputStream(path);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(entry);
            fos.close();
            outputStream.close();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() throws IOException {
        Entry entry = null;
        if(getFileSize() > 0) {
            try {
                InputStream fis = Files.newInputStream(path);
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                Object object = inputStream.readObject();
                fis.close();
                inputStream.close();
                entry = (Entry)object;
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        return entry;
    }
    public void remove() {
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
