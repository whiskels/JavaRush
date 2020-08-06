package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static int maxSquare(int[][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;
        int side = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i * j == 0) continue;
                if (matrix[i][j] == 1) matrix[i][j] = Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j], matrix[i - 1][j - 1])) + 1;
                if (matrix[i][j] > side) side = matrix[i][j];
            }
        }
        return side * side;
    }
}
