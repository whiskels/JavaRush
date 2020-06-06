package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject{

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        if (direction == null || gameObject == null) return false;

        int nX = this.getX();
        int nY = this.getY();
        switch (direction) {
            case RIGHT:
                nX += Model.FIELD_CELL_SIZE;
                break;
            case LEFT:
                nX -= Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                nY += Model.FIELD_CELL_SIZE;
                break;
            case UP:
                nY -= Model.FIELD_CELL_SIZE;
                break;
        }
        return (nX == gameObject.getX() && nY == gameObject.getY());
    }
}
