package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String first = r.readLine();
        String second = r.readLine();
            r.close();
            String line;
            BufferedReader firstreader = new BufferedReader(new FileReader(first));
            while ((line = firstreader.readLine()) != null) {
                allLines.add(line);
            }
            firstreader.close();
            BufferedReader secondreader = new BufferedReader(new FileReader(second));
            while ((line = secondreader.readLine()) != null) {
                forRemoveLines.add(line);
            }
            secondreader.close();
            Solution sol = new Solution();
            sol.joinData();
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();

        }
    }
}