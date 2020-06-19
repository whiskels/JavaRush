package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH = 14;
    private static  final int RACE_GOAL_CARS_COUNT = 40;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }

    private void createGame() {
        isGameStopped = false;
        player = new PlayerCar();
        roadMarking = new RoadMarking();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void moveAll() {
        player.move();
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME OVER", Color.WHITE, 24);
        player.stop();
        stopTurnTimer();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case UP:
                player.setSpeed(2);
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch(key) {
            case RIGHT:
                if (player.getDirection().equals(Direction.RIGHT)){
                    player.setDirection(Direction.NONE);
                }
                break;
            case LEFT:
                if (player.getDirection().equals(Direction.LEFT)){
                    player.setDirection(Direction.NONE);
                }
                break;
            case UP:
                player.setSpeed(1);
                break;
        }
    }

    @Override
    public void onTurn(int time) {
        if (roadManager.checkCrush(player)) {
            gameOver();
        } else {
            if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {
                finishLine.show();
            }

            if (finishLine.isCrossed(player)) {
                win();
            } else {
                score -= 5;
                setScore(score);
                moveAll();
                roadManager.generateNewRoadObjects(this);
            }
        }
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < WIDTH && x >= 0 && y >= 0 && y < HEIGHT) {
            super.setCellColor(x,y,color);
        }
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i == CENTER_X) {
                    setCellColor(i,j,Color.WHITE);
                } else if (i >= ROADSIDE_WIDTH && i < WIDTH - ROADSIDE_WIDTH) {
                    setCellColor(i,j,Color.DIMGREY);
                } else {
                    setCellColor(i,j,Color.GREEN);
                }
            }
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU WIN", Color.WHITE, 24);
        stopTurnTimer();
    }
}
