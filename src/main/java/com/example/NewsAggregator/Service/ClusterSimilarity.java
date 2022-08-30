package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.NewsCosineClustering.CosineSimilarity;
import com.example.NewsAggregator.NewsCosineClustering.StemmedWords;
import com.example.NewsAggregator.NewsCosineClustering.Stemmer;
import com.example.NewsAggregator.NewsCosineClustering.StopWords;
import com.example.NewsAggregator.Responses.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ClusterSimilarity {

    StopWords stopWords = new StopWords();
    Stemmer stemmer = new Stemmer();
    StemmedWords stemmedWords = new StemmedWords();

    public Double getSimilarity(List<List<NewsModel>> newsList, int cl1, int cl2) throws IOException {

        Map<Integer, Map<String, Integer>> wordToCountMapping = new HashMap<>();

        stopWords.generateStopWordsRegex();
        stemmedWords.mapStemmedWordtoRootWord();

        Map<String, Integer> globalFreq  = new HashMap<>();
        int count = 1;
        for(List<NewsModel> newsModels : newsList){
            for(NewsModel newsModel1 : newsModels){
                wordToCountMapping.put(count, getStringToCountMapping( Response.builder().description(newsModel1.getDescription())
                        .pubTime(newsModel1.getTime())
                        .link(newsModel1.getLink())
                        .title(newsModel1.getTitle())
                        .build(), globalFreq));
                count++;
            }
        }

        Map<String, Double> itf = new HashMap<>();
        for (Map.Entry<String,Integer> entry : globalFreq.entrySet()){
            String key = entry.getKey();
            Integer val = entry.getValue();
            if(val <= 0) {
                itf.put(key, 0.0d);
            } else
            {
                Double it = log2((count-1)*1.0/val);
                itf.put(key, it);
            }
        }

        Map<Integer, Map<String, Double>> score = new HashMap<>();
        for(int i = 1; i < count; i++){
            Map<String, Double> sc = new HashMap<>();
            for(String str : wordToCountMapping.get(i).keySet()){
                sc.put(str, (wordToCountMapping.get(i).get(str)*itf.get(str))/wordToCountMapping.get(i).size());
            }
            score.put(i, sc);
        }

        List<Integer> grpClr1 = new ArrayList<>();
        List<Integer> grpClr2 = new ArrayList<>();

        count = 1;
//        for(List<NewsModel> newsModels : newsList){
//            for(NewsModel newsModel1 : newsModels){
//               if(newsModel1.getClusterId() == cl1){
//                   grpClr1.add(count);
//               }
//               if(newsModel1.getClusterId() == cl2){
//                   grpClr2.add(count);
//               }
//               count++;
//            }
//        }
        Map<String, Double> grp1 = new HashMap<>();
        Map<String, Double> grp2 = new HashMap<>();

        for(Integer integer : grpClr1){
           Map<String, Double> temp = score.get(integer);

           for(String str : temp.keySet()){
               if(grp1.containsKey(str)){
                   grp1.put(str,grp1.get(str) + temp.get(str));
               }
               else{
                   grp1.put(str, temp.get(str));
               }
           }
        }

        for(Integer integer : grpClr2){
            Map<String, Double> temp = score.get(integer);

            for(String str : temp.keySet()){
                if(grp2.containsKey(str)){
                    grp2.put(str,grp2.get(str) + temp.get(str));
                }
                else{
                    grp2.put(str, temp.get(str));
                }
            }
        }

        return CosineSimilarity.cosineSimilarity(grp1, grp2);
    }

    public  Map<String, Integer> getStringToCountMapping(Response response, Map<String, Integer> globalFreq){
        Map<String, Integer> vector
                = new HashMap<>();

        String[] newStr = ((response.getTitle() + response.getTitle() + response.getDescription())
                .toLowerCase().replaceAll(stopWords.getStopWordsRegex(), ""))
                .replaceAll("\\p{Punct}", "")
                .split("\\s+");


        Set<String> included = new HashSet<>();
        for (String str : newStr) {
            String rootWord = stemmer.getRootWord(str); // using Stemmer Class (Porter Stemming Algo)
//            String rootWord = stemmedWords.getRootWord(str); // Using StemmedWord File
            if (vector.containsKey(rootWord)) vector.put(rootWord, vector.get(rootWord) + 1);
            else vector.put(rootWord, 1);
            if(globalFreq.containsKey(rootWord) && !included.contains(rootWord)) globalFreq.put(rootWord, globalFreq.get(rootWord) + 1);
            else globalFreq.put(rootWord, 1);
            included.add(rootWord);
        }

        return vector;
    }

    private  Double log2(double v) {
        return Math.log(v)/Math.log(2);
    }

}
