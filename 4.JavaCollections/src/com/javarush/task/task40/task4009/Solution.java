package com.javarush.task.task40.task4009;

/* 
Buon Compleanno!
*/

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Solution {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");

    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        LocalDate birth = LocalDate.parse(birthday, dtf);
        LocalDate currentBirth = birth.withYear(Year.parse(year).getValue());
        DayOfWeek dayOfweek = currentBirth.getDayOfWeek();
        return dayOfweek.getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}
