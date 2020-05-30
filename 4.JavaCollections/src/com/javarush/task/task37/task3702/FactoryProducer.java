package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static enum HumanFactoryType {
        MALE,
        FEMALE;
    }
    public static <T> AbstractFactory getFactory(HumanFactoryType type) {
        T result = null;
        if (type.equals(HumanFactoryType.MALE)) {
            result = (T) new MaleFactory();
        }
        else if (type.equals(HumanFactoryType.FEMALE)) {
            result = (T) new FemaleFactory();
        }
        return (AbstractFactory) result;
    }
}
