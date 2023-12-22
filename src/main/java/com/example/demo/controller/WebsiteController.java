package com.example.demo.controller;

// Controller


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Controller
public class WebsiteController {
    @GetMapping("/scrape")
    public String form(Model model) {
        model.addAttribute("websites", new ArrayList<>());
        model.addAttribute("search", "");
        return "form";
    }

    @PostMapping("/scrape")
    public String checkWebsites(@RequestParam String websites, @RequestParam String search, Model model) {
        String[] urls = websites.split("[ ,]+");
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
        model.addAttribute("websites", results);
        model.addAttribute("search", search);
        return "form";
    }
}