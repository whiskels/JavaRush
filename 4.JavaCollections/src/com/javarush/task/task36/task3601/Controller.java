package com.javarush.task.task36.task3601;

import java.util.List;

public class Controller {
    Model model = new Model();


    public List<String> onShowDataList() {
        return model.getStringDataList();
    }

}
