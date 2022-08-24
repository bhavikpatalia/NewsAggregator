package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.Enum.ClusteredNewsCSVFiles;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.NewsCosineClustering.ClusteringWithMerging;
import com.example.NewsAggregator.Responses.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.example.NewsAggregator.NewsDataStorage.WriteDataIntoCSVFile.writeClusteredNewsIntoCSV;

 class Status{
    Integer count;
    Integer clusterNumber;

    Status(){
        count = 1;
        clusterNumber = 1;
    }
}

@Slf4j
public class SaveNewsAfterClusteringIntoCSVFile {

    Status status = new Status();
    public void doClustering() throws IOException {
//        new FileWriter("/Users/vivek.me/NewsAggregator/ClusteredNews.csv", false);

        log.info("Making cluster for sports and storing clustered news into CSV file");
        doClusteringForSports();

        log.info("Making cluster for science and storing clustered news into CSV file");
        doClusteringForScience();

        log.info("Making cluster for technology and storing clustered news into CSV file");
        doClusteringForTechnology();

        log.info("Making cluster for health and storing clustered news into CSV file");
        doClusteringForHealth();

        log.info("Making cluster for business and storing clustered news into CSV file");
        doClusteringForBusiness();

        log.info("Making cluster for entertainment and storing clustered news into CSV file");
        doClusteringForEntertainment();

        log.info("Making cluster for auto and storing clustered news into CSV file");
        doClusteringForAuto();
    }

    private void doClusteringForAuto() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Auto");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.AUTO.getAction(), responseList, NewsCategory.AUTO, 1,1);
    }


    private void doClusteringForEntertainment() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Entertainment");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.ENTERTAINMENT.getAction(), responseList, NewsCategory.ENTERTAINMENT, 1,1);
    }

    private void doClusteringForBusiness() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Business");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.BUSINESS.getAction(), responseList, NewsCategory.BUSINESS, 1,1);
    }

    private void doClusteringForTechnology() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Technology");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.TECHNOLOGY.getAction(), responseList, NewsCategory.TECHNOLOGY, 1,1);
    }

    private void doClusteringForScience() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Science");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.SCIENCE.getAction(), responseList, NewsCategory.SCIENCE, 1,1);
    }

    private void doClusteringForHealth() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Health");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.HEALTH.getAction(), responseList, NewsCategory.HEALTH, 1,1);
    }

    private void doClusteringForSports() throws IOException {
        List<List<Response>> responseList = new ClusteringWithMerging().clusteringWithMerging("Sports");
        writeClusteredNewsIntoCSV(ClusteredNewsCSVFiles.SPORTS.getAction(), responseList, NewsCategory.SPORTS, 1,1);
    }

}
