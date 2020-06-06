package com.javarush.task.task21.task2113;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(Arrays.asList(
                new Horse("Plotva",3,0),
                new Horse("Legend",3,0),
                new Horse("Twinsen",3,0)));
        game.run();
        game.printWinner();
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }

    }

    public void move() {
        horses.stream()
                .forEach(horse -> horse.move());
    }

    public void print() {
        horses.stream()
                .forEach(horse -> horse.print());
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .orElse(null);
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", this.getWinner().getName());
    }
}
