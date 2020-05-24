package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;

    private int size = 0;
    private ArrayList<Entry> elements = new ArrayList<Entry>();

    public CustomTree() {
        this.root = new Entry<>("Root");

    }

    static class Entry<T> implements Serializable {


        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName){
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;

        }

        public void checkChildren(){
            if (leftChild != null)availableToAddLeftChildren = false;
            if (rightChild != null)availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren(){
            return ((availableToAddLeftChildren == true)||(availableToAddRightChildren == true));
        }
    }


    @Override
    public String get(int index){
        throw new UnsupportedOperationException();
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public String set(int index, String element){
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element){throw new UnsupportedOperationException();}

    @Override
    public String remove(int index){throw new UnsupportedOperationException();}

    @Override
    public List<String> subList(int fromIndex, int toIndex){throw new UnsupportedOperationException();}

    @Override
    public void removeRange(int fromIndex, int toIndex){throw new UnsupportedOperationException();}

    @Override
    public boolean addAll(int index, Collection<? extends String> c){throw new UnsupportedOperationException();}


    public String getParent(String s) {
        for (Entry entry : elements) {
            if (s.equals(root.elementName)) {
                return null;
            }
            if (entry == null) {
                return null;
            }
            if (entry.elementName.equals(s)) {
                return entry.parent.elementName;
            }
        }
        return null;
    }

    @Override
    public boolean add(String s){

        if (elements.size()==0 ) {
            root.lineNumber = 0;
            elements.add(root);}

        for (int i = 0; i < elements.size();i++){
            elements.get(i).checkChildren();
            if (elements.get(i).isAvailableToAddChildren()){
                if (elements.get(i).availableToAddLeftChildren == true){
                    Entry entry = new Entry(s);
                    entry.lineNumber = elements.get(i).lineNumber+1;
                    entry.parent = elements.get(i);
                    elements.get(i).leftChild = entry;
                    elements.add(entry);
                    size++;
                    elements.get(i).checkChildren();
                    break;
                }
                if (elements.get(i).availableToAddRightChildren == true){
                    Entry entry = new Entry(s);
                    entry.lineNumber = elements.get(i).lineNumber+1;
                    entry.parent = elements.get(i);
                    elements.get(i).rightChild = entry;
                    elements.add(entry);
                    size++;
                    elements.get(i).checkChildren();
                    break;
                }
            }
        }

        return true;
    }

    @Override
    public boolean remove(Object t) {
        try {String s =  String.valueOf(t);
            for (Entry<String> element : elements) {
                if (element.elementName.equals(s)) {
                    if (element.leftChild != null) {
                        remove(element.leftChild);
                    }
                    else if (element.rightChild != null) {
                        remove(element.rightChild);
                    }
                    if (element.equals(element.parent.leftChild)) {
                        element.parent.leftChild = null;
                        element.parent.availableToAddLeftChildren = true;
                    }
                    else if (element.equals(element.parent.rightChild)) {
                        element.parent.rightChild = null;
                        element.parent.availableToAddRightChildren = true;
                    }
                    element.parent = null;
                    element = null;
                    elements.remove(element);
                }
            }
        }
        catch (Exception e) {
            throw new UnsupportedOperationException();
        }
        return true;
    }

}
