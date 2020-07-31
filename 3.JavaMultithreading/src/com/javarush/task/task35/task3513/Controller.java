package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
    private static final int WINNING_TILE = 2048;
    private Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public void resetGame() {
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            resetGame();
        } else if (!model.canMove()) {
            view.isGameLost = true;
        } else if (!view.isGameLost && !view.isGameWon) {
            if (key == KeyEvent.VK_LEFT) model.left();
            if (key == KeyEvent.VK_UP) model.up();
            if (key == KeyEvent.VK_RIGHT) model.right();
            if (key == KeyEvent.VK_DOWN) model.down();
            if (key == KeyEvent.VK_Z) model.rollback();
            if (key == KeyEvent.VK_R) model.randomMove();
            if (key == KeyEvent.VK_A) model.autoMove();

            if (model.maxTile == WINNING_TILE) {
                view.isGameWon = true;
            }
        }

        view.repaint();
    }

    public View getView() {
        return view;
    }
}
