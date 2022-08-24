package com.example.NewsAggregator.Service;


import com.example.NewsAggregator.Enum.ClusteredNewsCSVFiles;
import com.example.NewsAggregator.Model.News;
import com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles;
import com.example.NewsAggregator.Responses.Response;

import java.util.List;

public class NewsServiceImplForCSV {

    public List<List<News>> getAllSportsNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.SPORTS.getAction(), false);
    }

    public List<List<News>> getAllScienceNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.SCIENCE.getAction(), false);
    }

    public List<List<News>> getAllEntertainmentNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.ENTERTAINMENT.getAction(), false);
    }

    public List<List<News>> getAllHealthNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.HEALTH.getAction(), false);
    }

    public List<List<News>> getAllBusinessNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.BUSINESS.getAction(), false);
    }

    public List<List<News>> getAllTechnologyNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.TECHNOLOGY.getAction(), false);
    }

    public List<List<News>> getAllAutoNews(){
        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.AUTO.getAction(), false);
    }

}
