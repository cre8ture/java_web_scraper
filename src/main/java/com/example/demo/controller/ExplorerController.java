package com.example.demo.controller;

import com.example.demo.service.ExploreService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExplorerController {
    @Autowired
    private ExploreService websiteExplorationService;

    @Autowired
    private WebClient webClient;
@PostMapping("/explore")
public String exploreWebsite(@RequestParam String website, @RequestParam String searchTerm, Model model) {
    List<String> results = new ArrayList<>();
    try {
        // Navigate to the website
        final HtmlPage page = webClient.getPage(website);
    
        // Start the recursive exploration
       results = websiteExplorationService.explorePage(page, searchTerm, results);


       System.out.println("RESULTS: " + results);
    } catch (IOException e) {
        model.addAttribute("error", "Failed to explore website: " + e.getMessage());
        return "error";
    }
    
    model.addAttribute("results", results);
    model.addAttribute("websites", new ArrayList<>());
    model.addAttribute("search", "");
    return "form";
}
}