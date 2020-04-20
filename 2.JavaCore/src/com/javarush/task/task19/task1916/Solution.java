package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr1 = new BufferedReader(new FileReader(r.readLine()));
        BufferedReader fr2 = new BufferedReader(new FileReader(r.readLine()));

        ArrayList<String> f1 = new ArrayList<String>();
        ArrayList<String> f2 = new ArrayList<String>();

        while (fr1.ready()) {
            f1.add(fr1.readLine());
        }
        while (fr2.ready()) {
            f2.add(fr2.readLine());
        }
        r.close();
        fr1.close();
        fr2.close();


        for (int i = 0; i < f2.size(); i++) {
            for (int j = 0; j < f1.size(); j++) {
                if (f1.get(j).equals(f2.get(i))) {
                    lines.add(new LineItem(Type.SAME,f1.get(j)));
                    i++;
                    j++;
                }
                else if (f1.get(j+1).equals(f2.get(i))) {
                    lines.add(new LineItem(Type.REMOVED,f1.get(j)));
                    lines.add(new LineItem(Type.SAME,f2.get(i)));
                    j += 2;
                    i++;
                }
                else if (f1.get(j).equals(f2.get(i+1))) {
                    lines.add(new LineItem(Type.ADDED, f2.get(i)));
                    i++;
                }
            }
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
