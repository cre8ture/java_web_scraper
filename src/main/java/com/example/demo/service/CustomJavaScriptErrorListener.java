package com.example.demo.service;

import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

import ch.qos.logback.classic.Logger;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.LoggerFactory;

public class CustomJavaScriptErrorListener implements JavaScriptErrorListener {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CustomJavaScriptErrorListener.class);


    @Override
    public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {
        logger.error("MalformedScriptURL on page " + page.getUrl() + ": " + url);
    }


    @Override
    public void scriptException(HtmlPage page, ScriptException scriptException) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scriptException'");
    }

    @Override
    public void timeoutError(HtmlPage page, long allowedTime, long executionTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'timeoutError'");
    }

    @Override
    public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadScriptError'");
    }

    @Override
    public void warn(String message, String sourceName, int line, String lineSource, int lineOffset) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    // Implement other methods if needed...
}
