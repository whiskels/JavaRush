package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 25;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped = false;
    private int countClosedTiles = SIDE*SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 2;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.GREY);
                setCellValue(x,y,"");
            }
        }
        countMineNeighbors();
        countFlags=countMinesOnField;
        score = 0;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (!gameField[i][j].isMine) {
                    gameField[i][j].countMineNeighbors = 0;
                    List<GameObject> neighbors = getNeighbors(gameField[i][j]);
                    for (GameObject cell : neighbors) {
                        if (cell.isMine) {
                            gameField[i][j].countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private void openTile(int x, int y) {
        if (!isGameStopped) {
            GameObject cell = gameField[y][x];
            if (!cell.isOpen && !cell.isFlag) {
                cell.isOpen = true;
                countClosedTiles--;
                if (cell.isMine) {
                    setCellValueEx(cell.x, cell.y, Color.RED, MINE);
                    gameOver();
                } else if (cell.countMineNeighbors == 0) {
                    score +=5;
                    setCellValue(cell.x, cell.y, "");
                    setCellColor(cell.x, cell.y, Color.GREEN);

                    List<GameObject> neighbors = getNeighbors(cell);
                    for (GameObject near : neighbors) {
                        if (!near.isOpen && !near.isMine) {
                            openTile(near.x, near.y);
                        }
                    }
                } else if (cell.countMineNeighbors != 0) {
                    score +=5;
                    setCellNumber(cell.x, cell.y, cell.countMineNeighbors);
                    setCellColor(cell.x, cell.y, Color.GREEN);
                }

                if (!cell.isMine && countClosedTiles==countMinesOnField) {
                    win();
                }
            }
        }
        setScore(score);
    }

    private void markTile(int x, int y) {
        if (!isGameStopped) {
            GameObject cell = gameField[y][x];
            if (!cell.isOpen) {
                if (countFlags != 0 && !cell.isFlag) {
                    cell.isFlag = true;
                    setCellValue(cell.x, cell.y, FLAG);
                    setCellColor(cell.x, cell.y, Color.YELLOW);
                    countFlags--;
                } else if (cell.isFlag) {
                    cell.isFlag = false;
                    setCellValue(cell.x, cell.y, "");
                    setCellColor(cell.x, cell.y, Color.GREY);
                    countFlags++;
                }
            }
        }
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME OVER", Color.GREEN, 36);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.ORANGE, "WIN-WIN", Color.RED, 36);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE*SIDE;
        countMinesOnField = 0;
        score = 0;
        setScore(score);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isGameStopped) {
            openTile(x,y);
        }
        else {
            restart();
        }
    }
    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x,y);
    }
}