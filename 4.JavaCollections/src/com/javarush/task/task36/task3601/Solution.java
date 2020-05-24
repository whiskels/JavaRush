package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/* 
MVC - простая версия
*/
public class Solution {
    public static void main(String[] args) {
        Service service = new Service();
        Model model = new Model();
        Controller controller = new Controller();

        View view = new View();
        view.fireShowDataEvent();
    }
}
