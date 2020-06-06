package com.javarush.task.task20.task2005;

import java.util.Objects;

public class Asset {
    public Asset(String name) {
        this.name = name;
    }

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;
        Asset asset = (Asset) o;
        return Double.compare(asset.getPrice(), getPrice()) == 0 &&
                getName().equals(asset.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }
}

