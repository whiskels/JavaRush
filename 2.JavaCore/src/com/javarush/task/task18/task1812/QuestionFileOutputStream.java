package com.javarush.task.task18.task1812;

/* 
Расширяем AmigoOutputStream
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuestionFileOutputStream implements AmigoOutputStream {
    private AmigoOutputStream amigo;

    public QuestionFileOutputStream(AmigoOutputStream amigo) {
        this.amigo = amigo;
    }

    @Override
    public void flush() throws IOException {
    amigo.flush();
    }

    @Override
    public void write(int b) throws IOException {
    amigo.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
    amigo.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
    amigo.write(b,off,len);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String line = r.readLine();
        if (line.equals("Д")) {
            amigo.close();
        }
    }
}

