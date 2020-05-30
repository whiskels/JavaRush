package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    String additionalValue;
    int currentPage = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> result = new ArrayList<>();
        Document document = null;
        try {
            document = getDocument(searchString, currentPage);
            while (true) {
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) {
                    currentPage = 0;
                    break;
                }
                for (Element element : elements) {
                    if (element != null) {
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.getElementsByAttributeValueContaining("data-qa", "title").text());
                        vac.setCity(element.getElementsByAttributeValueContaining("data-qa", "address").text());
                        vac.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                        vac.setSiteName(URL_FORMAT);
                        String urlPage = element.getElementsByAttributeValueContaining("data-qa", "title").attr("href");
                        vac.setUrl(urlPage);
                        String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation").text();
                        vac.setSalary(salary.length()==0 ? "" : salary);
                        result.add(vac);
                    }
                }
                ++currentPage;
                document = getDocument(searchString, currentPage);
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
