package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Color;

import java.awt.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape, platform;
    private boolean isGameStopped, isUpPressed, isLeftPressed, isRightPressed;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        showGrid(false);
        createGame();
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellColor(i,j,Color.ORANGE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }

    private void createGame() {
        createGameObjects();
        isLeftPressed = false;
        isRightPressed = false;
        isUpPressed = false;
        isGameStopped = false;
        score = 1000;
        setTurnTimer(50);
        drawScene();
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed,isLeftPressed,isRightPressed);
        if (score > 0) {
            score--;
        }
        check();
        setScore(score);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < HEIGHT && x >= 0 && y >= 0 && y < WIDTH) {
            super.setCellColor(x,y,color);
        }
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH/2,0);
        landscape = new GameObject(0,25,ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT-1, ShapeMatrix.PLATFORM);
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP :
                isUpPressed = true;
                break;
            case LEFT :
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case RIGHT :
                isRightPressed = true;
                isLeftPressed = false;
                break;
            case SPACE :
                if (isGameStopped) {
                    createGame();
                }
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP :
                isUpPressed = false;
                break;
            case LEFT :
                isLeftPressed = false;
                break;
            case RIGHT :
                isRightPressed = false;
                break;
        }
    }

    private void check() {
        if (rocket.isCollision(landscape) && !(rocket.isCollision(platform) && rocket.isStopped())) {
            gameOver();
        } else if (rocket.isCollision(platform) && rocket.isStopped()) {
            win();
        }

    }

    private void win() {
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU WIN", Color.WHITE, 24);
        stopTurnTimer();
    }

    private void gameOver() {
        rocket.crash();
        score = 0;
        isGameStopped = true;
        showMessageDialog(Color.RED, "YOU LOSE", Color.WHITE, 24);
        stopTurnTimer();
    }


}
