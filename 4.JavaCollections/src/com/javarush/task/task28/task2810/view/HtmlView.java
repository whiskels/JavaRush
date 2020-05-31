package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.vo.Vacancy;
import com.javarush.task.task28.task2810.Controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
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
        Document document = null;
        try {
            document = getDocument();
            Element templateVacancyByElement = document
                    .getElementsByClass("template").first();
            Element patternVacancyByElement = templateVacancyByElement.clone();
            patternVacancyByElement
                    .removeClass("template")
                    .removeAttr("style");
            document
                    .getElementsByAttributeValueEnding("class", "vacancy")
                    .remove();

            for (Vacancy vacancy : vacancies) {
                Element currentElement = patternVacancyByElement.clone();

                currentElement
                        .getElementsByClass("city").first()
                        .text(vacancy.getCity());
                currentElement
                        .getElementsByClass("companyName").first()
                        .text(vacancy.getCompanyName());
                currentElement
                        .getElementsByClass("salary").first()
                        .text(vacancy.getSalary());
                //currentElement.getElementsByAttribute("siteName").first().text(vacancy.getSiteName());

                Element link = currentElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateVacancyByElement.before(currentElement.outerHtml());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }


    private void updateFile(String content) {
        try (FileWriter os = new FileWriter(new File(filePath))) {
            os.write(content);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        File input = new File(filePath);
        return Jsoup.parse(input, "UTF-8");
    }
}
