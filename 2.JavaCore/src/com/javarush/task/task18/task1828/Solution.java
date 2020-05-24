package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    private static String fileName;
    private static ArrayList<String> fileContent;

    static {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            fileName = r.readLine();
            //fileName = "C:\\Users\\kuzmi\\Desktop\\crud.txt";
            r.close();
            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            String line;
            fileContent = new ArrayList<String>();
            while ((line = fr.readLine()) != null) {
                fileContent.add(line);
            }
            fr.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        boolean isUpdated = false;
        if (args[0].equals("-d") && args.length == 2) {
            isUpdated = true;
            fileContent.removeIf(line -> Integer.parseInt(line.substring(0, 7).replaceAll(" ", "")) == Integer.parseInt(args[1]));
        } else if (args[0].equals("-u") && args.length == 5) {
            isUpdated = true;
            String id = args[1];
            while (id.length() < 8) {
                id += " ";
            }
            String productName = args[2];
            while (productName.length() < 30) {
                productName += " ";
            }
            String price = args[3];
            while (price.length() < 8) {
                price += " ";
            }
            String quantity = args[4];
            while (quantity.length() < 4) {
                quantity += " ";
            }

            for (int i = 0; i < fileContent.size(); i++) {
                if (Integer.parseInt(fileContent.get(i).substring(0, 7).replaceAll(" ", "")) == Integer.parseInt(args[1])) {
                    fileContent.set(i,id + productName + price + quantity);
                }
            }
        }
        if (isUpdated) {
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName));
            for (String line : fileContent) {
                fw.write(line);
                fw.write("\r\n");
                System.out.println(line);
            }
            fw.close();
        }
    }
}
