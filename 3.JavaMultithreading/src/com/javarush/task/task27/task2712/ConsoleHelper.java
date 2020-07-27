package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    public static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bf.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        ConsoleHelper.writeMessage("Choose dishes. Enter 'exit' to stop.");
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        while (true) {
            String dishSelect = readString();
            if (dishSelect.equals("exit")) {
                break;
            }
            if (dishSelect.isEmpty()) {
                ConsoleHelper.writeMessage("No dish selected.");
                continue;
            }
            boolean isValid = false;
            for (Dish d : Dish.values())
                if (d.name().equalsIgnoreCase(dishSelect)) {
                    dishes.add(d);
                    isValid = true;
                }
            if (!isValid) {
                ConsoleHelper.writeMessage("Dish not found.");
            }

        }
        return dishes;
    }
}
