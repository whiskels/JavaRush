package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        // Check current level
        level = level % 60;
        if (level == 0) level = 60;

        // Create containers
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        int y;
        int x;

        // Read levels file here
        try (BufferedReader bf = new BufferedReader(new FileReader(levels.toFile()))) {
            String line;

            // Set initial positions
            int x0 = Model.FIELD_CELL_SIZE/2;
            int y0 = Model.FIELD_CELL_SIZE/2;
            while ((line = bf.readLine()) != null) {

                // If line is Maze: <level number> - read all info until maze starts
                if (line.equals("Maze: " + level)) {
                    String tmp;
                    while ((tmp = bf.readLine()) != null) {
                        if (tmp.startsWith("Length:")){
                            bf.readLine();
                            break;}
                    }
                    y = y0;
                    while ((line = bf.readLine()) != null) {
                        if (line.startsWith("************")) break; // Read until the end of the Maze
                        x = x0;
                        for (int i = 0; i < line.length(); i++) {
                            char c = line.charAt(i); // Get char representing Game Object
                            switch (c) {
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                            }
                            x += Model.FIELD_CELL_SIZE; // Increment y
                        }
                        y += Model.FIELD_CELL_SIZE; // Increment x
                    }
                }
            }
            return new GameObjects(walls, boxes, homes, player);
        } catch (Exception e) {
            return null;
        }
    }
}