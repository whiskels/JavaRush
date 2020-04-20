    package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.InputStreamReader;

    public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String f1 = r.readLine();
        String f2 = r.readLine();
        r.close();

        FileReader fr = new FileReader(f1);
        FileWriter fw = new FileWriter(f2);

        int i = 1;
        while (fr.ready()) {
            int data = fr.read();
            if (i % 2 == 0) {
                fw.write(data);
            }
            i++;
        }

        fr.close();
        fw.close();

    }
}
