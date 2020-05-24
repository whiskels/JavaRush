package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        while (br.ready()) {
            String line = br.readLine();
            String[] person = line.split("(?<=\\D)\\s(?=[0-9\\s])");
            PEOPLE.add(new Person(person[0], format.parse(person[1])));
        }
            br.close();
    }
}
