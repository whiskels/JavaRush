package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    int currentPage = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        Document document = null;
        try {
            while (true) {
                document = getDocument(searchString, currentPage++);
                Elements elements = document.getElementsByAttributeValue("class", "job");
                elements.addAll( document.getElementsByAttributeValue("class", "job marked"));

                if (elements.size() != 0) {
                    for (Element element : elements) {
                        Vacancy vacancy = new Vacancy();
                        vacancy.setTitle(element.getElementsByClass("title").first().text().trim());
                        vacancy.setSalary(element.getElementsByClass("salary").first().text().trim());
                        vacancy.setCity(element.select("span[class=location]").text().trim());
                        vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                        vacancy.setSiteName("https://moikrug.ru");
                        vacancy.setUrl("https://moikrug.ru" + element.select("div[class=title]").first().getElementsByTag("a").attr("href").trim());
                        result.add(vacancy);
                    }
                }
                else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String myURL = String.format(URL_FORMAT, searchString, page);

        Document doc = null;
        try {
            doc = Jsoup.connect(myURL)
                    .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36 RuxitSynthetic/1.0 v5196975962 t38550")
                    .referrer("https://www.google.com/")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }
}

