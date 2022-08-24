package com.example.NewsAggregator.NewsCosineClustering;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
public class StopWords {
    private String stopWordsRegex;

    public void generateStopWordsRegex(){
        try {
            List<String> stopWordsList = Files.readAllLines(Paths.get("src/main/java/com/example/NewsAggregator/English_StopWords.txt"));
            stopWordsRegex = stopWordsList.stream()
                    .collect(Collectors.joining("|", "\\b(", ")\\b\\s?"));

        } catch (IOException e) {
            log.error("Error while getting stop words from English_StopWords.txt");
            e.printStackTrace();
        }
    }


}
