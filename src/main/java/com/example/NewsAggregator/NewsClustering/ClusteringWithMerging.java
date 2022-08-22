package com.example.NewsAggregator.NewsClustering;

import com.example.NewsAggregator.Responses.Response;

import java.util.*;

import static com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles.getDataFromCSVFile;

public class ClusteringWithMerging {

    public static void main(String[] args){
        List<Response> dataFromCSVFile = getDataFromCSVFile("/Users/vivek.me/NewsAggregator/Sports.csv");

        Map<Integer, Map<String, Integer>> wordToCountMapping = new HashMap<>();

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

        Map<Integer, List<Integer>> cluster = new HashMap<>();
        for(int i = 1; i < 10; i++){
            List<Integer> grps = differentGrps.stream().toList();
            findSimilarity(grps, differentGrps, score, cluster);
        }

//        for (Map.Entry<Integer,List<Integer>> entry : cluster.entrySet()){
//            List<Integer> list = entry.getValue();
//            list.add(entry.getKey());
//            List<Response> responses = new ArrayList<>();
//            for(int i = 0; i < list.size(); i++){
//                responses.add(dataFromCSVFile.get(list.get(i)-1));
//            }
//            System.out.println(responses);
//        }
    }

    private static Double log2(double v) {
        return Math.log(v)/Math.log(2);
    }

    private static void findSimilarity(List<Integer> grps, Set<Integer> differentGrps, Map<Integer, Map<String, Double>> wordToCountMapping, Map<Integer, List<Integer>> cluster) {

        for(int i = 0; i < grps.size(); i++){
            for(int j = i+1; j < grps.size(); j++){
                Double cosineSimilarity = CosineSimilarity.cosineSimilarity(wordToCountMapping.get(grps.get(i)), wordToCountMapping.get(grps.get(j)));

                if(cosineSimilarity >= 0.6){
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
                        List<Integer> list = cluster.get(grps.get(i));
                        list.add(grps.get(j));
                        cluster.put(grps.get(i), list);
                    }
                    else{
                         cluster.put(grps.get(i), new ArrayList<>(grps.get(j)));
                    }
                    wordToCountMapping.remove(grps.get(j));
                }
            }
        }
    }

    public static Map<String, Integer> getStringToCountMapping(Response response, Map<String, Integer> globalFreq){
        Map<String, Integer> vector
                = new HashMap<>();

        String document = response.getTitle() + response.getTitle() + response.getDescription();
        String[] newStr = document.split("\\s+");

        Set<String> included = new HashSet<>();
        for (String str : newStr) {
            if (vector.containsKey(str)) vector.put(str, vector.get(str) + 1);
            else vector.put(str, 1);
            if(globalFreq.containsKey(str) && !included.contains(str)) globalFreq.put(str, globalFreq.get(str) + 1);
            else globalFreq.put(str, 1);
            included.add(str);
        }

        return vector;
    }
}
