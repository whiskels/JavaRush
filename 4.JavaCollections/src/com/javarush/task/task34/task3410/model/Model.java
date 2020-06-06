package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static int FIELD_CELL_SIZE = 20;
    public EventListener eventListener;
    public GameObjects gameObjects;
    public int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(new File(getClass().getResource("../res/levels.txt").getFile()).toPath());

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        } else {
            moveObject(player,direction);
            checkCompletion();
        }
    }

    public <T> boolean checkCollisionForGameObjects(CollisionObject currentObject, Set<T> collisionObjects, Direction direction) {
        for (T gameObject : collisionObjects) {
            if (currentObject.isCollision((CollisionObject) gameObject, direction)) return true;
        }
        return false;
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return checkCollisionForGameObjects(gameObject, gameObjects.getWalls(), direction);
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        boolean isCollision = false;
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (!checkWallCollision(box, direction) && !checkCollisionForGameObjects(box, boxes, direction)) {
                    moveObject(box, direction);
                } else isCollision = true;
            }
        }
        return isCollision;
    }

    public void moveObject(Movable movable, Direction direction) {
        switch (direction) {
            case UP:
                movable.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                movable.move(0, FIELD_CELL_SIZE);
                break;
            case LEFT:
                movable.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                movable.move(FIELD_CELL_SIZE, 0);
                break;
        }
    }

    public void checkCompletion() {
        boolean isHomeFilled = false;
        boolean isLevelCompleted = true;
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    isHomeFilled = true;
                }
            }
            if (!isHomeFilled) {
                isLevelCompleted = false;
                break;
            }
            isHomeFilled = false;
        }
        if (isLevelCompleted) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}