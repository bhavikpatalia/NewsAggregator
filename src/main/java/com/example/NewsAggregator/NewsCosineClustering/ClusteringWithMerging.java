package com.example.NewsAggregator.NewsCosineClustering;

import com.example.NewsAggregator.Responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import static com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles.getDataFromCSVFile;

@Slf4j
@Service
public class ClusteringWithMerging {

    StopWords stopWords = new StopWords();
    Stemmer stemmer = new Stemmer();
    StemmedWords stemmedWords = new StemmedWords();

    public List<List<Response>> clusteringWithMerging(String fileName) throws IOException {
        List<Response> dataFromCSVFile = getDataFromCSVFile(fileName + ".csv");

        Map<Integer, Map<String, Integer>> wordToCountMapping = new HashMap<>();

       stopWords.generateStopWordsRegex();
       stemmedWords.mapStemmedWordtoRootWord();

        Map<String, Integer> globalFreq  = new HashMap<>();
        int count = 1;
        Set<Integer> differentGrps = new HashSet<>();
        for(Response response : dataFromCSVFile){
            wordToCountMapping.put(count, getStringToCountMapping(response, globalFreq));
            differentGrps.add(count);
            count++;
        }

        Map<String, Double> itf = new HashMap<>();
        for (Map.Entry<String,Integer> entry : globalFreq.entrySet()){
            String key = entry.getKey();
            Integer val = entry.getValue();
            if(val <= 0) {
                itf.put(key, 0.0d);
            } else
            {
                Double it = log2(dataFromCSVFile.size()*1.0/val);
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

        Map<Integer, Set<Integer>> cluster = new HashMap<>();
        for(int i = 1; i < 10; i++){
            List<Integer> grps = differentGrps.stream().toList();
            findSimilarity(grps, differentGrps, score, cluster);
        }

        List<List<Integer>> grps = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 1; i<count; i++){
            if(!visited.contains(i)){
                List<Integer> newGrp = new ArrayList<>();
                newsMerging(i, newGrp, visited,cluster);
                grps.add(newGrp);
            }
        }

        return getSimilarNews(dataFromCSVFile, grps, count);
    }

    private  Double log2(double v) {
        return Math.log(v)/Math.log(2);
    }

    private  void findSimilarity(List<Integer> grps, Set<Integer> differentGrps, Map<Integer, Map<String, Double>> wordToCountMapping, Map<Integer, Set<Integer>> cluster) {

        for(int i = 0; i < grps.size(); i++){
            for(int j = i+1; j < grps.size(); j++){
                Double cosineSimilarity = CosineSimilarity.cosineSimilarity(wordToCountMapping.get(grps.get(i)), wordToCountMapping.get(grps.get(j)));

                if(cosineSimilarity >= 0.4){
                    differentGrps.remove(grps.get(j));

                    for(String str : wordToCountMapping.get(grps.get(j)).keySet()){
                        if(wordToCountMapping.get(grps.get(i)).containsKey(str)){
                            wordToCountMapping.get(grps.get(i)).put(str, wordToCountMapping.get(grps.get(i)).get(str) + wordToCountMapping.get(grps.get(j)).get(str));
                        }
                        else{
                            wordToCountMapping.get(grps.get(i)).put(str, wordToCountMapping.get(grps.get(j)).get(str));
                        }
                    }
                    if(cluster.containsKey(grps.get(i))){
                        Set<Integer> list = cluster.get(grps.get(i));
                        list.add(grps.get(j));
                        cluster.put(grps.get(i), list);
                    }
                    else{
                         cluster.put(grps.get(i), new HashSet<>(grps.get(j)));
                    }
                    wordToCountMapping.remove(grps.get(j));
                }
            }
        }
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

    private void newsMerging(int i, List<Integer> grp, Set<Integer> visited, Map<Integer, Set<Integer>> cluster) {
        if(visited.contains(i))return;

        visited.add(i);
        grp.add(i);

        if(!cluster.containsKey(i)) return;
        for(Integer integer : cluster.get(i)){
            newsMerging(integer, grp, visited, cluster);
        }
    }

    private List<List<Response>> getSimilarNews(List<Response> dataFromCSVFile, List<List<Integer>> grps, int count) {
        Set<Integer> visited = new HashSet<>();

        List<List<Response>> similarNews = new ArrayList<>();

        for (List<Integer> list : grps){
            List<Response> responses = new ArrayList<>();
            for(Integer integer : list){
                visited.add(integer);
                responses.add(dataFromCSVFile.get(integer-1));
            }
            similarNews.add(responses);
        }

        for(int i = 1; i < count; i++){
            if(!visited.contains(i)){
                List<Response> responses = new ArrayList<>();
                responses.add(dataFromCSVFile.get(i-1));
                similarNews.add(responses);
            }
        }
        return similarNews;
    }
}
