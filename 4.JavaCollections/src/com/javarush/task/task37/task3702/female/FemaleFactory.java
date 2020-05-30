package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;
import com.javarush.task.task37.task3702.male.KidBoy;
import com.javarush.task.task37.task3702.male.TeenBoy;

public class FemaleFactory implements AbstractFactory {
    public <T> Human getPerson(int age) {
        T result = null;
        if (age <= KidBoy.MAX_AGE) {
            result = (T) new KidGirl();
        }
        else if (age <= TeenBoy.MAX_AGE) {
            result = (T) new TeenGirl();
        }
        else  {
            result = (T) new Woman();
        }
        return (Human) result;
    }
}

