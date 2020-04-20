package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
    private List<GameObject> snakeParts = new ArrayList<GameObject>();
    private final static String HEAD_SIGN = "\uD83D\uDC7E";
    private final static String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        super(x,y);
        snakeParts.add(new GameObject(x,y));
        snakeParts.add(new GameObject(x+1,y));
        snakeParts.add(new GameObject(x+2,y));
    }

    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject currentPart = snakeParts.get(i);
            if (i == 0) {
                if (isAlive) game.setCellValueEx(currentPart.x,currentPart.y,Color.NONE,HEAD_SIGN, Color.ANTIQUEWHITE,75);
                else game.setCellValueEx(currentPart.x,currentPart.y,Color.NONE,HEAD_SIGN, Color.RED,75);
            }
            else {
                if (isAlive) game.setCellValueEx(currentPart.x,currentPart.y,Color.NONE,BODY_SIGN,Color.ANTIQUEWHITE,75);
            else game.setCellValueEx(currentPart.x,currentPart.y,Color.NONE,BODY_SIGN, Color.RED,75);
            }
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {

    }
}
