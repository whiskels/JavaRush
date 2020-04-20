package com.javarush.task.task18.task1827;

/* 
Прайсы
*/


import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();

        if (args.length == 4 && args[0].equals("-c")) {
            String productName = args[1];
            while (productName.length() < 31) {
                productName += " ";
            }
            String price = args[2];
            while (price.length() < 8) {
                price += " ";
            }
            String quantity = args[3];
            while (quantity.length() < 4) {
                quantity += " ";
            }

            BufferedReader fr = new BufferedReader(new FileReader(fileName));
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
            String strLine = null, tmp;

            while ((tmp = fr.readLine()) != null) {
                strLine = tmp;
            }
            fr.close();

            String currentId = strLine.substring(0, 7).replaceAll(" ", "");
            int intCurrentId = Integer.parseInt(currentId) + 1;
            fw.write(intCurrentId + productName + price + quantity);
            fw.close();
        }
    }
}
