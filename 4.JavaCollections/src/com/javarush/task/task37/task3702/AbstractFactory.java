package com.javarush.task.task37.task3702;

public interface AbstractFactory {
    public <T> Human getPerson(int age);
}
