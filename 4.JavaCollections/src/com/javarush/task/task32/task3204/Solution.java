package com.javarush.task.task32.task3204;




import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;


/* 
Генератор паролей
*/
public class Solution {
    static final String nums = "0123456789";
    static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] pw = new char[8];
        for (int i = 0; i < pw.length; i++) {
            pw[i] = (char) 33;
        }
        pw[rnd.nextInt(8)] = nums.charAt(rnd.nextInt(nums.length()));
        pw[rnd.nextInt(8)] = lowerCase.charAt(rnd.nextInt(lowerCase.length()));
        pw[rnd.nextInt(8)] = upperCase.charAt(rnd.nextInt(upperCase.length()));

        for (int i = 0; i < pw.length; i++) {
            if (pw[i] == (char) 33) {
                int currentCharType = rnd.nextInt(100);
                if (currentCharType <= 33) {
                    pw[i] = nums.charAt(rnd.nextInt(nums.length()));
                } else if (currentCharType <= 66) {
                    pw[i] = lowerCase.charAt(rnd.nextInt(lowerCase.length()));
                } else {
                    pw[i] = upperCase.charAt(rnd.nextInt(upperCase.length()));
                }
            }
        baos.write(pw[i]);
        }

        return baos;
    }
}