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

    public boolean checkCollision(GameObject object) {
        boolean isCollision = false;
        for (GameObject snakePart : snakeParts) {
            if (snakePart.x == object.x && snakePart.y == object.y) {
                isCollision = true;
            }
        }
        return isCollision;
    }

    public void setDirection(Direction direction) {
            boolean oppositeDirection = false;
            boolean badDirection = false;
            if (((direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) && snakeParts.get(0).x != snakeParts.get(1).x)
        || ((direction.equals(Direction.DOWN) || direction.equals(Direction.UP)) && snakeParts.get(0).y != snakeParts.get(1).y)) {
            badDirection = true;
            }
        switch (direction) {
            case UP:
                oppositeDirection = this.direction.equals(Direction.DOWN);
                break;
            case DOWN:
                oppositeDirection = this.direction.equals(Direction.UP);
                break;
            case LEFT:
                oppositeDirection = this.direction.equals(Direction.RIGHT);
                break;
            case RIGHT:
                oppositeDirection = this.direction.equals(Direction.LEFT);
                break;
        }
        if (!oppositeDirection && !badDirection) {
                this.direction = direction;
            }
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x < 0 || newHead.x > SnakeGame.WIDTH - 1 || newHead.y < 0 || newHead.y > SnakeGame.HEIGHT - 1 ) {
            isAlive = false;
            return;
        }
        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }
        snakeParts.add(0,newHead);
        if (snakeParts.get(0).x == apple.x && snakeParts.get(0).y == apple.y){
        apple.isAlive = false;
        }
        else {
            removeTail();
        }
    }

    public int getLength() {
        return snakeParts.size();
    }

    public GameObject createNewHead() {
        GameObject currentHead = snakeParts.get(0);
        GameObject newHead = null;
        switch (direction) {
            case UP:
                newHead = new GameObject(currentHead.x,currentHead.y-1);
                break;
            case DOWN:
                newHead = new GameObject(currentHead.x,currentHead.y+1);
                break;
            case RIGHT:
                newHead = new GameObject(currentHead.x+1,currentHead.y);
                break;
            case LEFT:
                newHead = new GameObject(currentHead.x-1,currentHead.y);
                break;
        }
        return newHead;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size()-1);
    }
}
