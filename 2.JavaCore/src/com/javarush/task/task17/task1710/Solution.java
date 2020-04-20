package com.javarush.task.task17.task1710;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        if (args.length != 0) {
            switch (args[0]) {
                case ("-c"):
                    if (args[2].equals("м")) {
                        allPeople.add(Person.createMale(args[1], input.parse(args[3])));
                        System.out.println(allPeople.size() - 1);
                    } else if (args[2].equals("ж")) {
                        allPeople.add(Person.createFemale(args[1], input.parse(args[3])));
                        System.out.println(allPeople.size() - 1);
                    }
                    break;
                    case ("-u") :
                        if (args[3].equals("м")) {
                            allPeople.set(Integer.parseInt(args[1]), Person.createMale(args[2], input.parse(args[4])));
                        } else if (args[3].equals("ж")) {
                            allPeople.set(Integer.parseInt(args[1]), Person.createFemale(args[2], input.parse(args[4])));
                        }
                        break;
                    case ("-d") :
                        allPeople.get(Integer.parseInt(args[1])).setName(null);
                        allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
                        allPeople.get(Integer.parseInt(args[1])).setSex(null);
                        break;
                    case ("-i") :
                        System.out.print(allPeople.get(Integer.parseInt(args[1])).getName() + " " + (allPeople.get(Integer.parseInt(args[1])).getSex().equals(Sex.MALE) ? "м" : "ж") + " " + output.format(allPeople.get(Integer.parseInt(args[1])).getBirthDate()));
                        // info
                        break;
                    }
            }
        }
    }

