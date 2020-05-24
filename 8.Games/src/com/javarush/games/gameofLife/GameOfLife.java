package com.javarush.games.gameofLife;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife extends Game {

    private static final int HEIGHT = 25;
    private static final int WIDTH = 25;
    private static final int initialAliveChance = 15;
    private static Cell[][] gameField = new Cell[HEIGHT][WIDTH];
    private static ArrayList<Cell> allCells = new ArrayList<Cell>();
    static SecureRandom scr = new SecureRandom();
    public int score = 0;
    private boolean isGameStopped;


    @Override
    public void initialize() {
        setScreenSize(HEIGHT, WIDTH);
        createGame();
    }

    public void createCells() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boolean cellType = scr.nextInt(100) <= initialAliveChance;
                gameField[i][j] = new Cell(j, i, cellType);
                allCells.add(gameField[i][j]);
            }
        }
    }

    public void createGame() {
        isGameStopped = false;
        createCells();
        countAliveNeighbors();
        drawField();
        setTurnTimer(100);
    }

    public void drawField() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (gameField[i][j].isAlive) {
                    setCellColor(i, j, Color.GREEN);
                } else {
                    setCellColor(i, j, Color.GREY);
                }
            }
        }
    }

    @Override
    public void onTurn(int time) {
        if (isGameStopped) {
            changeScene();
            setScore(score++);
            drawField();
        }
    }

    public static void changeScene() {
        countAliveNeighbors();
        for (Cell cell : allCells) {
            if (cell.countAliveNeighbors == 3 && !cell.isAlive) {
                cell.isAlive = true;
            } else if (cell.isAlive) {
                if (cell.countAliveNeighbors > 3 || cell.countAliveNeighbors < 2) {
                    cell.isAlive = false;
                }
            }
        }
    }

    private static List<Cell> getNeighbors(Cell cell) {
        List<Cell> result = new ArrayList<>();
        for (int y = cell.y - 1; y <= cell.y + 1; y++) {
            for (int x = cell.x - 1; x <= cell.x + 1; x++) {
                if (y < 0 || y >= HEIGHT) {
                    continue;
                }
                if (x < 0 || x >= WIDTH) {
                    continue;
                }
                if (gameField[y][x] == cell) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private static void countAliveNeighbors() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                gameField[i][j].countAliveNeighbors = 0;
                List<Cell> neighbors = getNeighbors(gameField[i][j]);
                for (Cell cell : neighbors) {
                    if (cell.isAlive) {
                        gameField[i][j].countAliveNeighbors++;
                    }
                }
            }
        }
    }

    public void setAlive(int x, int y) {
        gameField[y][x].isAlive = true;
        setCellColor(y,x,Color.GREEN);
    }

    public void setDead(int x, int y) {
        gameField[y][x].isAlive = false;
        setCellColor(y,x,Color.GREY);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        setAlive(y, x);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        setDead(y, x);
    }

    @Override
    public void onKeyPress(Key key) {
        if (key.equals(Key.SPACE) && isGameStopped) {
            isGameStopped = false;
        }
        else if (key.equals(Key.SPACE) && !isGameStopped) {
            isGameStopped = true;
        }
    }
}

