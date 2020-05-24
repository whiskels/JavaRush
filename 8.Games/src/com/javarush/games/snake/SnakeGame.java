package com.javarush.games.snake;

import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Color;

import java.awt.*;
import java.lang.reflect.WildcardType;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static  final int GOAL = 20;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();

    }

    private void createGame() {
        this.snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        this.turnDelay = 300;
        isGameStopped = false;
        score = 0;
        setScore(score);
        setTurnTimer(turnDelay);
        drawScene();
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.GREEN,"YOU WIN", Color.WHITE, 24);
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.RED,"GAME OVER", Color.ORANGE, 24);
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int time) {
        snake.move(apple);
        if (!apple.isAlive) {
            score += 5;
            turnDelay -= 10;
            setTurnTimer(turnDelay);
            setScore(score);
            createNewApple();
        }
        if (!snake.isAlive)
            gameOver();
        if (snake.getLength() > GOAL) {
            win();
        }
        drawScene();
    }

    private void createNewApple() {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
            if (snake.checkCollision(apple)) {
                createNewApple();
            }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case SPACE:
                if (isGameStopped == true) {
                    createGame();
                    break;
                }
        }
    }
}
