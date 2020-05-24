package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));

        String inputLine;
        String result = "";
        while ((inputLine = br.readLine()) != null) {
            String[] inputArray = inputLine.split(" ");
            if (inputArray.length != 0) {
                for (int i = 0; i < inputArray.length; i++) {
                    if (inputArray[i].matches(".*\\d.*")) {
                        result += inputArray[i] + " ";
                        System.out.println(inputArray[i]);
                    }
                }
            }
        }
        br.close();
        bw.write(result);
        bw.close();
    }
}
