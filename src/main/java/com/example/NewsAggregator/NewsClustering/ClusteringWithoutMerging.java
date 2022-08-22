package com.example.NewsAggregator.NewsClustering;

import com.example.NewsAggregator.Responses.Response;

import java.util.*;

import static com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles.getDataFromCSVFile;

public class ClusteringWithoutMerging {

    public static void main(String[] args){
        List<Response> dataFromCSVFile = getDataFromCSVFile("/Users/vivek.me/NewsAggregator/Technology.csv");

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
        for (List<Integer> list : grps){
            List<Response> responses = new ArrayList<>();
            if(list.size() == 1)continue;
            for(Integer integer : list){
                System.out.print(integer + " ");
                responses.add(dataFromCSVFile.get(integer-1));
            }
            System.out.println(responses);
        }
    }

    private static Double log2(double v) {
        return Math.log(v)/Math.log(2);
    }

    private static void findSimilarity(List<Integer> grps, Set<Integer> differentGrps, Map<Integer, Map<String, Double>> wordToCountMapping, Map<Integer, Set<Integer>> cluster) {

        for(int i = 0; i < grps.size(); i++){
            for(int j = i+1; j < grps.size(); j++){

                Double cosineSimilarity = CosineSimilarity.cosineSimilarity(wordToCountMapping.get(grps.get(i)), wordToCountMapping.get(grps.get(j)));
                if(cosineSimilarity >= 0.72){
                    if(cluster.containsKey(grps.get(i))){
                        Set<Integer> list = cluster.get(grps.get(i));
                        list.add(grps.get(j));
                        cluster.put(grps.get(i), list);
                    }
                    else{
                        cluster.put(grps.get(i), new HashSet<>(grps.get(j)));
                    }
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

    private static void newsMerging(int i, List<Integer> grp, Set<Integer> visited, Map<Integer, Set<Integer>> cluster) {
        if(visited.contains(i))return;

        visited.add(i);
        grp.add(i);

        if(!cluster.containsKey(i)) return;
        for(Integer integer : cluster.get(i)){
            newsMerging(integer, grp, visited, cluster);
        }

    }
}

