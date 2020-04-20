package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;

public class EnemyShip extends Ship {
    public int score = 15;

    public EnemyShip(double x, double y) {
        super(x, y);
        setStaticView(ShapeMatrix.ENEMY);
    }

    public void move(Direction direction, double speed) {
        switch (direction) {
            case RIGHT:
                this.x +=speed;
                break;
            case LEFT:
                this.x -= speed;
                break;
            case DOWN:
                this.y += 2;
                break;
        }
    }
    @Override
    public Bullet fire() {
    double bx = x+1;
    double by = y+height;
    return new Bullet((int)bx, (int) by, Direction.DOWN);
    }

    @Override
    public void kill() {
        if (isAlive) {
            isAlive = false;
            super.setAnimatedView(false,ShapeMatrix.KILL_ENEMY_ANIMATION_FIRST,ShapeMatrix.KILL_ENEMY_ANIMATION_SECOND, ShapeMatrix.KILL_ENEMY_ANIMATION_THIRD);
        }
    }
}
