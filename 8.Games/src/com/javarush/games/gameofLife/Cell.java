package com.javarush.games.gameofLife;

public class Cell {
    public int x;
    public int y;
    public boolean isAlive;
    public static final String aliveSymbol = "X";
    public static final String deadSymbol = "-";
    public int countAliveNeighbors;


    public Cell(int x, int y, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

}
