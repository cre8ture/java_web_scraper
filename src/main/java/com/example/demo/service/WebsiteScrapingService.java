package com.example.demo.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class WebsiteScrapingService {
    public List<String> scrapeWebsites(String[] urls, String search) {
        List<String> results = new ArrayList<String>();
        for (String url : urls) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.body().select("*:containsOwn(" + search + ")");
                for (Element element : elements) {
                    results.add(url + ": " + element.outerHtml());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}