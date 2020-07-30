package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score = 0;
    protected int maxTile = 0;

    public Model() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        resetGameTiles();
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

    private void compressTiles(Tile[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value == 0) {
                for (int j = i; j < tiles.length; j++) {
                    if (tiles[j].value != 0) {
                        tiles[i].value = tiles[j].value;
                        tiles[j].value = 0;
                        break;
                    }
                }
            }
        }
    }

    private void mergeTiles(Tile[] tiles) {
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value * 2;
                maxTile = Math.max(maxTile, tiles[i].value);
                score += tiles[i].value;
                tiles[i + 1].value = 0;
                compressTiles(tiles);
            }
        }
    }
}
