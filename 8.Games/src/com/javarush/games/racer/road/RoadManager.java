package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.GameObject;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    public final static int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public final static int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private final static int FIRST_LANE_POSITION = 16;
    private final static int FOURTH_LANE_POSITION = 44;
    private final static int PLAYER_CAR_DISTANCE = 12;
    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type.equals(RoadObjectType.THORN)) {
            return new Thorn(x,y);
        } else if (type.equals(RoadObjectType.DRUNK_CAR)) {
            return new MovingCar(x, y);
        } else {
            return new Car(type, x, y);
        }
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        final int x = game.getRandomNumber(FIRST_LANE_POSITION,FOURTH_LANE_POSITION);
        final int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type,x,y);

        if (object != null && isRoadSpaceFree(object)) {
            items.add(object);
        }
    }

    public void draw(Game game) {
        for (RoadObject r : items) {
            r.draw(game);
        }
    }

    public void move(int boost) {
        for (RoadObject r : items) {
            r.move(r.speed + boost, items);
        }

        deletePassedItems();
    }

    private boolean isThornExists() {
        for (RoadObject r : items) {
            if (r.type.equals(RoadObjectType.THORN)) {
                return true;
            }
        }

        return false;
    }

    private void generateThorn(Game game) {
        if (game.getRandomNumber(100) < 10 && !isThornExists()) {
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    public boolean checkCrush(PlayerCar car) {
        for (GameObject o : items) {
            if (o.isCollision(car)) {
                return true;
            }
        }
        return false;
    }

    private void deletePassedItems() {
        Iterator<RoadObject> iterator = items.iterator();

        while (iterator.hasNext()) {
            RoadObject o = iterator.next();

            if (o.y >= RacerGame.HEIGHT) {
                if (!(o instanceof Thorn)) {
                    passedCarsCount++;
                }
                iterator.remove();
            }
        }
    }


    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void generateRegularCar(Game game) {
        final int carTypeNumber = game.getRandomNumber(4);

        if (game.getRandomNumber(100) < 30) {
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject o : items) {
            if (o.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }

        return true;
    }

    private void generateMovingCar(Game game) {
        if (game.getRandomNumber(100) < 10 && !isMovingCarExists()) {
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    private boolean isMovingCarExists() {
        for (GameObject o : items) {
            if (o.getClass() == MovingCar.class) {
                return true;
            }
        }

        return false;
    }

    public int getPassedCarsCount() {
        return passedCarsCount;
    }
}
