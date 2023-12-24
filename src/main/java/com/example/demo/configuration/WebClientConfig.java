package com.example.demo.configuration;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;

import com.gargoylesoftware.htmlunit.WebClientOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        WebClient webClient = new WebClient();
        WebClientOptions options = webClient.getOptions();
        options.setJavaScriptEnabled(false); // Set to false to disable JavaScript
        // Other configurations
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        return webClient;
    }
}