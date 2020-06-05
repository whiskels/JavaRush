package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class GameObject {
    public int x,y, height, width;
    public int[][] matrix;

    public GameObject(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.width = matrix[0].length;
        this.height = matrix.length;
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Game game) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                game.setCellColor(this.x + j, this.y + i, Color.values()[matrix[i][j]]);
            }
        }
    }
}
