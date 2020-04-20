package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class Star extends GameObject {
    private static final String STAR_SIGN = "\u2605";

    public Star(double x, double y) {
        super(x, y);
    }

    public void draw(Game game) {
        int j = (int) x;
        int i = (int) y;
        game.setCellValueEx(j, i, Color.NONE, STAR_SIGN, Color.YELLOW, 100);
    }
}
