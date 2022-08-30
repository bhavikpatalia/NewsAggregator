package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Responses.Response;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFromCSVFiles {

    public static List<Response> getDataFromCSVFile(String path){
        List<Response> responses = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                Response response = new Response();
                response.setTitle(row[1]);
                response.setPubTime(Long.parseLong(row[2]));
                response.setDescription(row[3]);
                response.setLink(row[4]);
                response.setImageUrl(row[5]);
                responses.add(response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

    public static List<List<NewsModel>> getDataFromCSVFile(String path, boolean type){
        List<List<NewsModel>> responses = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(path);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            Map<Long, List<Long>> mappingOfCluster = new HashMap<>();
            for(String[] row : allData){
                List<Long> list;
                if(!mappingOfCluster.containsKey(Long.parseLong(row[6]))){
                    list = new ArrayList<>();
                    list.add(Long.parseLong(row[0]));
                    mappingOfCluster.put(Long.parseLong(row[6]), list);
                }
                else{
                    list = mappingOfCluster.get(Long.parseLong(row[6]));
                    list.add(Long.parseLong(row[0]));
                    mappingOfCluster.put(Long.parseLong(row[6]), list);
                }
            }

            for(Long l : mappingOfCluster.keySet()){
                List<Long> newsIndex = mappingOfCluster.get(l);
                List<NewsModel> response = new ArrayList<>();
                for(Long index : newsIndex){
                    String[] row = allData.get((int) (index-1));
                    response.add(NewsModel.builder().newsId(Integer.parseInt(row[0]))
                            .title(row[1])
                            .time(Long.parseLong(row[2]))
                            .description(row[3])
                            .link(row[4])
                            .imageURL(row[5])
                            .clusterId(Integer.parseInt(row[6]))
                            .newsCategory(NewsCategory.valueOf(row[7]))
                            .build());
                }
                responses.add(response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }
}