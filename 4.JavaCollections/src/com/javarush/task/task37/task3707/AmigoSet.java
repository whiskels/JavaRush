package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Set, Serializable, Cloneable {
    private final static Object PRESENT = null;
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<E, Object>(map);
    }

    public AmigoSet(Collection<? extends E> collection){
        // реализуй это
        map = new HashMap<E, Object>();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object e) {
        return map.put((E) e, PRESENT)==null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
