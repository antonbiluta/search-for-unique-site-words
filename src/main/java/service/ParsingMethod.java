package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParsingMethod {

    private static final Logger log = LogManager.getLogger();


    public static Map<String, Integer> ParseSite(String url) {
        String[] words = contentToStringArray(url);
        return countUniqueWords(words);
    }

    private static Document getPage(String url) {
        try {
            return Jsoup.parse(new URL(url), 3000);
        } catch (IOException ex) {
            log.warn("IOException in ParsingMethod", ex);
            return null;
        }
    }

    public static String[] contentToStringArray(String url) {
        String[] listOfSeparators = {" ", "\"", "'", ",", ".", "!", "?", "(", ")", "[", "]", "-", ";", ":", "\n", "\r", "\t"};
        String pageText = Objects.requireNonNull(getPage(url)).text().toUpperCase();
        String separatorsString = String.join("|\\", listOfSeparators);
        return pageText.split(separatorsString);
    }

    private static Map<String, Integer> countUniqueWords(String[] words) {
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            if (word.matches("\\b[A-Za-zА-Яа-яЁё]*")) {
                int newValue = counter.getOrDefault(word, 0) + 1;
                counter.put(word, newValue);
            }
        }
        counter.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(stringIntegerEntry -> {
                    log.info(stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue());
                });
        return counter;
    }


}
