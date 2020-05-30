package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {
    public <T> Human getPerson(int age) {
        T result = null;
        if (age <= KidBoy.MAX_AGE) {
            result = (T) new KidBoy();
        }
        else if (age <= TeenBoy.MAX_AGE) {
            result = (T) new TeenBoy();
        }
        else  {
            result = (T) new Man();
        }
        return (Human) result;
    }
}
