package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    public static final int COMPLEXITY = 5;
    private List<Bullet> enemyBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private List<Bullet> playerBullets;
    private static final int PLAYER_BULLETS_MAX = 1;
    private int score;

    @Override
    public void initialize() {
        this.setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int time) {
        moveSpaceObjects();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }
        setScore(score);
        check();
        drawScene();
    }

    private void createGame() {
        isGameStopped = false;
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<Bullet>();
        playerBullets = new ArrayList<Bullet>();
        playerShip = new PlayerShip();
        animationsCount = 0;
        score = 0;
        drawScene();
        setTurnTimer(40);
    }

    private void drawScene() {
        drawField();
        playerShip.draw(this);
        enemyFleet.draw(this);
        for (Bullet bullets : enemyBullets) {
           bullets.draw(this);
        }
        for (Bullet bullets : playerBullets) {
            bullets.draw(this);
        }
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i,j,Color.GREY,"");
            }
        }
        for (Star star : stars) {
            star.draw(this);
        }
    }
    private void createStars() {
        stars = new ArrayList<Star>();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(getRandomNumber(WIDTH), getRandomNumber(HEIGHT)));
        }
    }

    private void moveSpaceObjects() {
        playerShip.move();
        enemyFleet.move();
        for (Bullet bullets : enemyBullets) {
            bullets.move();
        }
        for (Bullet bullets : playerBullets) {
            bullets.move();
        }
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(bullets -> bullets.y >= HEIGHT - 1);
        enemyBullets.removeIf(bullets -> !bullets.isAlive);
        playerBullets.removeIf(bullets -> bullets.y + bullets.height < 0);
        playerBullets.removeIf(bullets -> !bullets.isAlive);
    }

    private void check() {
        if (enemyFleet.getBottomBorder() >= playerShip.y) {
            playerShip.kill();
        }
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        playerShip.verifyHit(enemyBullets);
        if (!playerShip.isAlive) {
            stopGameWithDelay();
        }
        removeDeadBullets();
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) {
            showMessageDialog(Color.GREEN, "WIN-WIN", Color.GREEN, 32);
        }
        else {
            showMessageDialog(Color.RED,"LOSE-LOSE",Color.RED,32);
        }
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        if (key.equals(Key.SPACE) && isGameStopped) {
            createGame();
        }
        else if (key.equals(Key.SPACE)) {
            Bullet bullet = playerShip.fire();
            if (bullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                playerBullets.add(bullet);
            }
        }
        else if (key.equals(Key.LEFT)) {
            playerShip.setDirection(Direction.LEFT);
        }
        else if (key.equals(Key.RIGHT)) {
            playerShip.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key.equals(key.LEFT) && playerShip.getDirection().equals(Direction.LEFT)) {
            playerShip.setDirection(Direction.UP);
        }
        else if (key.equals(key.RIGHT) && playerShip.getDirection().equals(Direction.RIGHT)) {
            playerShip.setDirection(Direction.UP);
        }
    }

    @Override
    public void setCellValueEx(int a, int b, Color color, String string) {
        if (a > 0 && a < HEIGHT && b > 0 && b < WIDTH) {
            super.setCellValueEx(a,b,color,string);
        }
    }
}
