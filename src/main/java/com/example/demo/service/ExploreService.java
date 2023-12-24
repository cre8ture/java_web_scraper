package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
// import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExploreService {
    public int count = 0;
    @Autowired
    private WebClient webClient; // Autowired WebClient

    private static final Logger logger = LoggerFactory.getLogger(ExploreService.class);

    public List<String> explorePage(HtmlPage page, String inputString, List<String> results) throws IOException {
        List<HtmlForm> forms = page.getForms();
        System.out.println("FORMS: " + forms);
        this.count++;
        for (HtmlForm form : forms) {
            System.out.println("Form action URL: " + form.getActionAttribute());
            System.out.println("Form method: " + form.getMethodAttribute());
        }
        for (HtmlForm htmlForm : forms) {
            if (this.count >= 20) {
                break;
            }
            List<HtmlInput> inputs = htmlForm.getByXPath(".//input");
            for (HtmlInput input : inputs) {
                if (input instanceof HtmlInput) {
                    System.out.println("i am input");
                    // Convert the HtmlInput to a string and append it to results
                    results.add(input.asXml());
                    // Type into the input if it's a text input or password input
                    if (input instanceof HtmlTextInput || input instanceof HtmlPasswordInput) {
                        input.type(inputString);
                    }
                }
            }
            try {
                // Submit the form and explore the resulting page
                HtmlElement submitElement = htmlForm.getFirstByXPath(".//button");
                if (submitElement != null) {
                    // Introduce a delay of 1 second
                    Thread.sleep(1000);

                    HtmlPage nextPage = submitElement.click();
                    // Recursively explore the next page
                    System.out.println("i am results");
                    explorePage(nextPage, inputString, results);
                } else
                    System.out.println("i am null as");
            } catch (IOException e) {
                logger.error("Failed to submit form and explore the resulting page", e);
            } catch (InterruptedException e) {
                logger.error("Sleep interrupted", e);
            }
        }
          // Return the results list
    return results;
    }
}