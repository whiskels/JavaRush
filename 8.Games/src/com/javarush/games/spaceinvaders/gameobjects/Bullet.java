package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class Bullet extends GameObject {
    private Direction direction;
    private int dy;
    public boolean isAlive = true;

    public Bullet(double x, double y, Direction direction) {
        super(x, y);
        this.direction = direction;
        if (direction.equals(Direction.UP)) {
            dy = -1;
        }
        else {
            dy = 1;
        }
        setMatrix(ShapeMatrix.BULLET);
    }

    public void move() {
        y += dy;
    }

    public void kill() {
        isAlive = false;
    }
}
