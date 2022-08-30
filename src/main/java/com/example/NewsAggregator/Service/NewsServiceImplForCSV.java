package com.example.NewsAggregator.Service;


import com.example.NewsAggregator.Enum.ClusteredNewsCSVFiles;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Model.UIResponseModel;
import com.example.NewsAggregator.NewsDataStorage.ReadFromCSVFiles;
import com.example.NewsAggregator.NewsDataStorage.ReadFromMySqlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NewsServiceImplForCSV {

    @Autowired
    ClusterSimilarity clusterSimilarity;

    @Autowired
    ReadFromMySqlDatabase readFromMySqlDatabase;

    public List<List<UIResponseModel>> getAllSportsNews(){
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.SPORTS.getAction(), false);
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.SPORTS);
    }

    public List<List<UIResponseModel>> getAllScienceNews(){
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.SCIENCE);
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.SCIENCE.getAction(), false);
    }

    public List<List<UIResponseModel>> getAllEntertainmentNews(){
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.ENTERTAINMENT);
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.ENTERTAINMENT.getAction(), false);
    }

    public List<List<UIResponseModel>> getAllHealthNews(){
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.HEALTH);
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.HEALTH.getAction(), false);
    }

    public List<List<UIResponseModel>> getAllBusinessNews(){
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.BUSINESS);
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.BUSINESS.getAction(), false);
    }

    public List<List<UIResponseModel>> getAllTechnologyNews(){
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.TECHNOLOGY);
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.TECHNOLOGY.getAction(), false);
    }

    public List<List<UIResponseModel>> getAllAutoNews(){
        return readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.AUTO);
//        return ReadFromCSVFiles.getDataFromCSVFile(ClusteredNewsCSVFiles.AUTO.getAction(), false);
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
