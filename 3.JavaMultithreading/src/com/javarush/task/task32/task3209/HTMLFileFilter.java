package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String name = f.getName().toLowerCase();
        if (f.isDirectory() || name.endsWith(".html") || name.endsWith(".htm")) {
            return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
