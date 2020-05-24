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
        //String fileName1 = "C://Users//kuzmi//Desktop//crud.txt";
        //String fileName2 = "C://Users//kuzmi//Desktop//output.txt";
        String fileName1 = r.readLine();
        String fileName2 = r.readLine();

        BufferedReader fr1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader fr2 = new BufferedReader(new FileReader(fileName2));

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


        int i = 0;
            for (int j = 0; j < f1.size(); j++ ) {
                if (i < f2.size()) {
                    if (f1.get(j).equals(f2.get(i))) {
                        lines.add(new LineItem(Type.SAME, f1.get(j)));
                        i++;
                    } else if (j + 1 < f1.size() && f1.get(j + 1).equals(f2.get(i))) {
                        lines.add(new LineItem(Type.REMOVED, f1.get(j)));
                        lines.add(new LineItem(Type.SAME, f2.get(i)));
                        i++;
                    } else if (i + 1 < f2.size() && f1.get(j).equals(f2.get(i + 1))) {
                        lines.add(new LineItem(Type.ADDED, f2.get(i)));
                        lines.add(new LineItem(Type.SAME, f1.get(j)));
                        i += 2;
                    }
                }
                else {
                    lines.add(new LineItem(Type.REMOVED, f1.get(j)));
                }

            }
        for (; i < f2.size(); i++) {
            lines.add(new LineItem(Type.ADDED, f2.get(i)));
        }

        for (LineItem line : lines) {
            System.out.println(line.type + line.line);
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
