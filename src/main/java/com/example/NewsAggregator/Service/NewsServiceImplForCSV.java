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
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.SPORTS), cl1, cl2, NewsCategory.SPORTS);
    }

    public Double getScienceNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.SCIENCE), cl1, cl2, NewsCategory.SCIENCE);
    }

    public Double getBusinessNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.BUSINESS), cl1, cl2, NewsCategory.BUSINESS);
    }

    public Double getAutoNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.AUTO), cl1, cl2, NewsCategory.AUTO);
    }

    public Double getTechnologyNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.TECHNOLOGY), cl1, cl2, NewsCategory.TECHNOLOGY);
    }

    public Double getEntertainmentNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.ENTERTAINMENT), cl1, cl2, NewsCategory.ENTERTAINMENT);
    }

    public Double getHealthNewsSimilarity(int cl1, int cl2) throws IOException {
        return clusterSimilarity.getSimilarity(readFromMySqlDatabase.readClusteredNewsFromDatabase(NewsCategory.HEALTH), cl1, cl2, NewsCategory.HEALTH);
    }

}
