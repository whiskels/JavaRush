package com.javarush.task.task18.task1814;

import java.io.FileInputStream;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {


    public TxtInputStream(String fileName) throws Exception {
        super(fileName);
        if (!fileName.endsWith(".txt")) {
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    public static void main(String[] args) {
    }
}

