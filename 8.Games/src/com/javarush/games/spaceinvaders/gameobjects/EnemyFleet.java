package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnemyFleet {
private static final int ROWS_COUNT = 3;
private static final int COLUMNS_COUNT = 10;
private static final int STEP = ShapeMatrix.ENEMY.length + 1;
private List<EnemyShip> ships;
private Direction direction = Direction.RIGHT;

public EnemyFleet() {
    createShips();
}
public void draw(Game game) {
    for (EnemyShip ship : ships) {
        ship.draw(game);
    }
}

private void createShips() {
    ships = new ArrayList<EnemyShip>();
    for (int i = 0; i < ROWS_COUNT; i++) {
        for (int j = 0; j < COLUMNS_COUNT; j++) {
        ships.add(new EnemyShip(j * STEP, 12 + i * STEP));
        }
    }
    ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
}

    private double getLeftBorder() {
        double leftBorder = ships.get(0).x;
        for (EnemyShip ship : ships) {
            if (ship.x < leftBorder) {
                leftBorder = ship.x;
        }
        }
        return leftBorder;
    }
    private double getRightBorder() {
        double rightBorder = ships.get(0).x;
        for (EnemyShip ship : ships) {
            if (ship.x > rightBorder) {
                rightBorder = ship.x;
            }
        }
        rightBorder += ships.get(0).width;
        return rightBorder;
    }

    private double getSpeed() {
    double speed = (double) 3.0 / ships.size();
    speed = speed < 2 ? speed : 2;
    return speed;
    }

    public void move() {
    if (ships.size() > 0) {
        Direction isChanged = direction;
        if (direction.equals(Direction.LEFT) && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
        }
        else if (direction.equals(Direction.RIGHT) && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
        }
        double speed = getSpeed();
        if (!isChanged.equals(direction)) {
            for (EnemyShip ship : ships) {
                ship.move(Direction.DOWN, speed);
            }
        }
        else {
            for (EnemyShip ship : ships) {
                ship.move(direction, speed);
            }
        }
    }
    }

    public Bullet fire(Game game) {
    Bullet bullet = null;
    if (ships.size() == 0) {
        return bullet;
    }
    else {
        int a = game.getRandomNumber(100/SpaceInvadersGame.COMPLEXITY);
        if (a > 0) {
            return bullet;
        }
        else {
            int b = game.getRandomNumber(ships.size());
            bullet = ships.get(b).fire();
        }
    }
    return bullet;
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.size() > 0) {
            int output = 0;
            for (EnemyShip ship : ships) {
                for (Bullet bullet : bullets) {
                    if (bullet.isAlive && ship.isAlive) {
                        if (ship.isCollision(bullet)) {
                            ship.kill();
                            bullet.kill();
                            output += ship.score;
                        }
                    }
                }
            }
            return output;
        }
        return 0;
    }

    public void deleteHiddenShips() {
    ships.removeIf(ship -> !ship.isVisible());
    }

    public double getBottomBorder() {
    double min = 0;
    if (ships.size() > 0) {
    min = ships.get(1).y + ships.get(1).height;
    for (EnemyShip ship : ships) {
        min = Math.max(min, ship.y + ship.height);
    }
    }
    return min;
    }

    public int getShipsCount() {
    return ships.size();
    }
}