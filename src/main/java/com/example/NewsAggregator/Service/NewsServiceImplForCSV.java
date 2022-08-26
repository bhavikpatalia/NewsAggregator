package com.example.NewsAggregator.Service;


import com.example.NewsAggregator.Enum.ClusteredNewsCSVFiles;
import com.example.NewsAggregator.Model.News;
import com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles;
import com.example.NewsAggregator.Responses.Response;

import java.io.IOException;
import java.util.List;

public class NewsServiceImplForCSV {

    ClusterSimilarity clusterSimilarity = new ClusterSimilarity();

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

    public Double getSportsNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.SPORTS.getAction(), false), cl1, cl2);
    }

    public Double getScienceNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.SCIENCE.getAction(), false), cl1, cl2);
    }

    public Double getBusinessNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.BUSINESS.getAction(), false), cl1, cl2);
    }

    public Double getAutoNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.AUTO.getAction(), false), cl1, cl2);
    }

    public Double getTechnologyNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.TECHNOLOGY.getAction(), false), cl1, cl2);
    }

    public Double getEntertainmentNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.ENTERTAINMENT.getAction(), false), cl1, cl2);
    }

    public Double getHealthNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.HEALTH.getAction(), false), cl1, cl2);
    }

}
