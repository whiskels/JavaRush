package com.javarush.task.task34.task3410.model;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameObjects {
    public Set<Wall> walls;
    public Set<Box> boxes;
    public Set<Home> homes;
    public Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<GameObject> getAll() {
        return Stream.of(walls,boxes,homes, Collections.singleton(player))
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
