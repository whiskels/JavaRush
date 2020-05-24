package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int polygonCount = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {

                if (a[i][j] == 1) {
                    int rightY = i;
                    int rightX = j;
                    for (int y = rightY+1; y < a.length; y++) {
                        if (a[y][j] == 0) {
                            rightY = y - 1;
                            break;
                        }
                        else if ((y == a.length - 1) && (a[y][j] == 1)) {
                            rightY = a.length - 1;
                        }
                    }
                    for (int x = rightX + 1; x < a[i].length; x++) {
                        if (a[rightY][x] == 0) {
                            rightX = x - 1;
                            break;
                        }
                        else if ((x == a[i].length - 1) && (a[rightY][x] == 1)) {
                            rightX = a[i].length - 1;
                        }
                    }
                    polygonCount++;
                    for (int y = i; y <= rightY; y++) {
                        for (int x = j; x <= rightX; x++) {
                            a[y][x] = 0;
                        }
                    }
                }
            }
        }



        return polygonCount;
    }

}
