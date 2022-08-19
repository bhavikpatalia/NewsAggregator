package NewsClustering;

import Responses.Response;

import java.util.*;

import static NewsDataStorage.ReadFromCSVFiles.getDataFromCSVFile;

public class Clustering {

    public static void main(String[] args){
        List<Response> dataFromCSVFile = getDataFromCSVFile("/Users/vivek.me/NewsAggregator/Science.csv");

        Map<Integer, Map<String, Integer>> wordToCountMapping = new HashMap<>();

        int count = 1;
        Set<Integer> differentGrps = new HashSet<>();
        for(Response response : dataFromCSVFile){
            wordToCountMapping.put(count, getStringToCountMapping(response));
            differentGrps.add(count);
            count++;
        }

        Map<Integer, List<Integer>> cluster = new HashMap<>();
        for(int i = 1; i < 10; i++){
            List<Integer> grps = differentGrps.stream().toList();
            findSimilarity(grps, differentGrps, wordToCountMapping, cluster);
        }
    }

    private static void findSimilarity(List<Integer> grps, Set<Integer> differentGrps, Map<Integer, Map<String, Integer>> wordToCountMapping, Map<Integer, List<Integer>> cluster) {

        for(int i = 0; i < grps.size(); i++){
            for(int j = i+1; j < grps.size(); j++){
                Double cosineSimilarity = CosineSimilarity.cosineSimilarity(wordToCountMapping.get(grps.get(i)), wordToCountMapping.get(grps.get(j)));

                if(cosineSimilarity >= 0.50){
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

    public static Map<String, Integer> getStringToCountMapping(Response response){
        Map<String, Integer> vector
                = new HashMap<>();

        String document = response.getTitle() + response.getTitle() + response.getDescription();
        String[] newStr = document.split("\\s+");

        for (String str : newStr)
            if (vector.containsKey(str)) vector.put(str, vector.get(str) + 1);
            else vector.put(str, 1);


        return vector;
    }
}
