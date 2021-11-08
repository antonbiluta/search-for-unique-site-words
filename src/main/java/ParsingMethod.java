import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParsingMethod {

    private static final Logger log = LogManager.getLogger();

    private static Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    public static String ParseSite(String url) throws IOException {

        String[] listOfSeparators = {" ", "\"", "'",",",".", "!","?","(",")","[","]","-",";",":","\n","\r","\t"};

        String pagetext = getPage(url).text();
        String separatorsString = String.join("|\\", listOfSeparators);
        String[] words = pagetext.split(separatorsString);

        Map<String, Integer> counter = new HashMap<>();

        for(String word:words) {
            if (word.matches("\\b[A-Za-zА-Яа-яЁё]*")) {
                int newValue = counter.getOrDefault(word, 0) + 1;
                counter.put(word, newValue);
            }
        }
        log.info("Number of unique words. Site: "+ url);
        counter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .forEach(stringIntegerEntry -> {
                    System.out.println(stringIntegerEntry.getKey().toUpperCase()+" - "+stringIntegerEntry.getValue());
                });
        return "Complete";
    }
}
