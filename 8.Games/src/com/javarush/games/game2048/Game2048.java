package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE,SIDE);
        createGame();
        drawScene();
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        setScore(score);
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(i,j,gameField[j][i]);
            }
        }
    }

    private void createNewNumber() {
        if (getMaxTileValue() == 2048) {
            win();
        }
        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);
        if (gameField[y][x] == 0) {
            int chance = getRandomNumber(10);
            gameField[y][x] = chance == 9 ? 4 : 2;
        } else {
            createNewNumber();
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        Color color = getColorByValue(value);
        if (value != 0) {
            setCellValueEx(x, y, color, String.valueOf(value));
        } else {
            setCellValueEx(x, y, color, "");
        }
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0 :
                return Color.WHITE;
            case 2 :
                return Color.LIGHTGRAY;
            case 4 :
                return Color.LIGHTGREY;
            case 8:
                return Color.DIMGREY;
            case 16:
                return Color.GREY;
            case 32:
                return Color.DARKSLATEGREY;
            case 64:
                return Color.ORANGE;
            case 128:
                return Color.DARKORANGE;
            case 256:
                return Color.INDIANRED;
            case 512:
                return Color.ORANGERED;
            case 1024:
                return Color.MEDIUMVIOLETRED;
            case 2048:
                return Color.DARKRED;
        }
        return Color.WHITE;
    }

    private boolean compressRow(int[] row) {
        boolean isCompressed = false;
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 0) {
                for (int j = i; j < row.length; j++) {
                    if (row[j] != 0) {
                        row[i] = row[j];
                        row[j] = 0;
                        isCompressed = true;
                        break;
                    }
                }
            }
        }
        return isCompressed;
    }

    private boolean mergeRow(int[] row) {
        boolean isMerged = false;
        for (int i = 0; i < row.length-1 ; i++) {
            if (row[i] == row[i+1] && row[i] !=0) {
                row[i] = row[i]*2;
                score+=row[i];
                setScore(score);
                row[i+1] = 0;
                i++;
                isMerged = true;
            }
        }
        return isMerged;
    }

    @Override
    public void onKeyPress(Key key) {
        if (!isGameStopped) {
            if (!canUserMove()) {
                gameOver();
                return;
            }
            switch(key) {
                case LEFT:
                    moveLeft();
                    drawScene();
                    break;
                case RIGHT:
                    moveRight();
                    drawScene();
                    break;
                case DOWN:
                    moveDown();
                    drawScene();
                    break;
                case UP:
                    moveUp();
                    drawScene();
                    break;
            }
        } else if (key.equals(Key.SPACE)) {
            isGameStopped = false;
            createGame();
            drawScene();
        }
    }


    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveLeft() {
        boolean isAdditionalPointNeeded = false;
        for (int i = 0; i < SIDE; i++) {
            boolean c1 = compressRow(gameField[i]);
            boolean c2 = mergeRow(gameField[i]);
            compressRow(gameField[i]);
            if (c1 || c2) {
                isAdditionalPointNeeded = true;
            }
        }
        if (isAdditionalPointNeeded) {
            createNewNumber();
        }
    }

    private void  moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] buffer = new int[SIDE][SIDE];

        for(int i = 0, c = 0; i < SIDE; i++,c++){
            for(int j = SIDE - 1, k = 0; j >= 0; j--,k++){
                buffer[c][k] = gameField[j][i];
            }
        }
        gameField = buffer;
    }

    private int getMaxTileValue() {
        int max = 0;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] > max) {
                    max = gameField[i][j];
                }
            }
        }
        return max;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU WIN", Color.WHITE, 24);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "YOU LOSE", Color.WHITE, 24);

    }

    private boolean canUserMove() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] == 0) {
                    return true;
                }
                if (hasEqualNeighbor(j,i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasEqualNeighbor(int x, int y) {
        if (x+1 < SIDE && gameField[y][x] == gameField[y][x+1]) return true;
        if (x-1 >= 0 && gameField[y][x] == gameField[y][x-1]) return true;
        if (y+1 < SIDE && gameField[y][x] == gameField[y+1][x]) return true;
        if (y-1 >= 0 && gameField[y][x] == gameField[y-1][x]) return true;
        return false;
    }
}
