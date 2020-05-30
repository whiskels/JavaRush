package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.vo.Vacancy;
import com.javarush.task.task28.task2810.Controller;

import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace('.', '/') + "/vacancies.html";

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odeassa");
    }


    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        return null;
    }
    private void updateFile(String content) {

    }
}
