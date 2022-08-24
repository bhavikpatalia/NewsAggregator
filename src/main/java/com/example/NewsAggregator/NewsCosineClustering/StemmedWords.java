package com.example.NewsAggregator.NewsCosineClustering;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class StemmedWords {

    Map<String, String> stemmedWordMap = new HashMap<>();

    public void mapStemmedWordtoRootWord() throws IOException {

        List<String> stopWordsList = Files.readAllLines(Paths.get("src/main/java/com/example/NewsAggregator/StemmedWords.txt"));
        for(String string : stopWordsList){
            String[] newStr = string.split(":");
            stemmedWordMap.put(newStr[0], newStr[1]);
        }
    }

    public String getRootWord(String word){
        return stemmedWordMap.getOrDefault(word, word);
    }


}
