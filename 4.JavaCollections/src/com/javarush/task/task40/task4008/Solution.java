package com.javarush.task.task40.task4008;

/* 
Работа с Java 8 DateTime API
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

public class Solution {
    private static final DateTimeFormatter DTF_DATE = DateTimeFormatter.ofPattern("d.M.yyyy");
    private static final DateTimeFormatter DTF_TIME = DateTimeFormatter.ofPattern("H:m:s");


    public static void main(String[] args) {
        printDate("9.10.2017 5:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        final boolean isDate = date.contains(".");
        final boolean isTime = date.contains(":");
        final boolean isFull = date.contains(" ");
        LocalDate parsedDate = null;
        LocalTime parsedTime = null;

        if (isFull) {
            String[] parts = date.split(" ");
            parsedDate = LocalDate.parse(parts[0], DTF_DATE);
            parsedTime = LocalTime.parse(parts[1], DTF_TIME);
        } else if (isTime) {
            parsedTime = LocalTime.parse(date, DTF_TIME);
        } else if (isDate) {
            parsedDate = LocalDate.parse(date, DTF_DATE);
        }

        if (parsedDate != null) {
            print(parsedDate);
        }
        if (parsedTime != null) {
            print(parsedTime);
        }
    }

    private static void print(LocalDate d) {
        System.out.println(String.format("День: %d\n" +
                        "День недели: %d\n" +
                        "День месяца: %d\n" +
                        "День года: %d\n" +
                        "Неделя месяца: %d\n" +
                        "Неделя года: %d\n" +
                        "Месяц: %d\n" +
                        "Год: %d",
                d.getDayOfMonth(),
                d.getDayOfWeek().getValue(),
                d.getDayOfMonth(),
                d.getDayOfYear(),
                d.get(WeekFields.ISO.weekOfMonth()),
                d.get(WeekFields.ISO.weekOfYear()),
                d.getMonth().getValue(),
                d.getYear()));
    }

    private static void print(LocalTime d) {
        System.out.println(String.format("AM или PM: %s\n" +
                        "Часы: %d\n" +
                        "Часы дня: %d\n" +
                        "Минуты: %d\n" +
                        "Секунды: %d",
                d.getHour() >= 12 ? "PM" : "AM",
                d.getHour() >= 12 ? d.getHour() - 12 : d.getHour(),
                d.getHour(),
                d.getMinute(),
                d.getSecond()));
    }
}
