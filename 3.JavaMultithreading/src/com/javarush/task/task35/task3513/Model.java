package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    protected int score = 0;
    protected int maxTile = 0;
    private Tile[][] gameTiles;
    private Stack<Integer> previousScores;
    private Stack<Tile[][]> previousStates;
    private boolean isSaveNeeded = true;

    public Model() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        resetGameTiles();
        previousScores = new Stack<>();
        previousStates = new Stack<>();
    }

    protected void resetGameTiles() {
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                gameTiles[y][x] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> result = new ArrayList<>();
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (gameTiles[y][x].isEmpty()) {
                    result.add(gameTiles[y][x]);
                }
            }
        }

        return result;
    }

    public void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() != 0) {
            emptyTiles.get((int) (emptyTiles.size() * Math.random())).value = (Math.random() < 0.9 ? 2 : 4);
        }
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isCompressed = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value == 0) {
                for (int j = i; j < tiles.length; j++) {
                    if (tiles[j].value != 0) {
                        tiles[i].value = tiles[j].value;
                        tiles[j].value = 0;
                        isCompressed = true;
                        break;
                    }
                }
            }
        }
        return isCompressed;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isMerged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value * 2;
                maxTile = Math.max(maxTile, tiles[i].value);
                score += tiles[i].value;
                tiles[i + 1].value = 0;
                compressTiles(tiles);
                isMerged = true;
            }
        }
        return isMerged;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }


        boolean isNewTileNeed = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i])) {
                isNewTileNeed = true;
            }
            if (mergeTiles(gameTiles[i])) {
                isNewTileNeed = true;
            }
        }
        if (isNewTileNeed) {
            addTile();
        }

        isSaveNeeded = true;
    }

    private void rotateClockwise() {
        Tile[][] buffer = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0, c = 0; i < FIELD_WIDTH; i++, c++) {
            for (int j = FIELD_WIDTH - 1, k = 0; j >= 0; j--, k++) {
                buffer[c][k] = gameTiles[j][i];
            }
        }
        gameTiles = buffer;
    }

    public void up() {
        saveState(gameTiles);

        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
    }

    public void right() {
        saveState(gameTiles);

        rotateClockwise();
        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
    }

    public void down() {
        saveState(gameTiles);

        rotateClockwise();
        left();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if(gameTiles[i][j].value == 0)
                    return true;
                if((i + 1) < gameTiles.length && gameTiles[i][j].value == gameTiles[i+1][j].value)
                    return true;
                if((j + 1) < gameTiles.length && gameTiles[i][j].value == gameTiles[i][j+1].value)
                    return true;
            }
        }
        return false;
    }

    private void saveState(Tile[][] field) {
        Tile[][] copy = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int y = 0; y < FIELD_WIDTH; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                copy[y][x] = new Tile(gameTiles[y][x].value);
            }
        }

        previousStates.push(copy);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100) % 4);
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        Tile[][] lastBoard = previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (lastBoard[i][j].value != gameTiles[i][j].value) {
                    return true;
                }
            }
        }

        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();

        if (hasBoardChanged()) {
            rollback();
            return new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return new MoveEfficiency(-1, 0, move);
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));

        priorityQueue.poll().getMove().move();
    }
}
