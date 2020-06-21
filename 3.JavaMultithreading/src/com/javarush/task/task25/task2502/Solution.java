package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            final String[] wheely = loadWheelNamesFromDB();
            if (wheely.length != 4) {
                throw new IllegalArgumentException();
            }

            for (String s : wheely) {
                try {
                    wheels.add(Wheel.valueOf(s));
                } catch (IllegalArgumentException e) {
                    System.out.println("Not a car");
                    throw e;
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            return new String[]{"UP", "FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
    }
}
