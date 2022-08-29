package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.Enum.CSVFiles;
import com.example.NewsAggregator.NewsGenerators.*;
import com.example.NewsAggregator.Responses.Response;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.NewsAggregator.NewsDataStorage.WriteDataIntoCSVFile.writeDataForCustomSeparatorCSV;


@Slf4j
@Service
public class UpdateNewsDatabase {


    SaveNewsAfterClusteringIntoCSVFile saveNewsAfterClusteringIntoCSVFile = new SaveNewsAfterClusteringIntoCSVFile();

    @Scheduled(fixedDelay = 2*60000L, initialDelay = 0L)
    public void updateNewsDatabase() throws IOException {

        log.info("Updating Science News into CSV File");
        ScienceNewsGenerator scienceNewsGenerator = new ScienceNewsGenerator();
        List<Response> parsedNews = scienceNewsGenerator.getAllScienceNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.SCIENCE.getAction());
        parsedNews.clear();


        log.info("Updating Auto News into CSV File");
        AutoNewsGenerator autoNewsGenerator = new AutoNewsGenerator();
        parsedNews = autoNewsGenerator.getAllAutoNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.AUTO.getAction());
        parsedNews.clear();


        log.info("Updating Business News into CSV File");
        BusinessNewsGenerator businessNewsGenerator = new BusinessNewsGenerator();
        parsedNews = businessNewsGenerator.getAllBusinessNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.BUSINESS.getAction());
        parsedNews.clear();


        log.info("Updating Entertainment News into CSV File");
        EntertainmentNewsGenerator entertainmentNewsGenerator = new EntertainmentNewsGenerator();
        parsedNews = entertainmentNewsGenerator.getAllEntertainmentNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.ENTERTAINMENT.getAction());
        parsedNews.clear();


        log.info("Updating Health News into CSV File");
        HealthNewsGenerator healthNewsGenerator = new HealthNewsGenerator();
        parsedNews = healthNewsGenerator.getAllHealthNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.HEALTH.getAction());
        parsedNews.clear();


        log.info("Updating Sports News into CSV File");
        SportsNewsGenerator sportsNewsGenerator = new SportsNewsGenerator();
        parsedNews = sportsNewsGenerator.getAllSportsNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.SPORTS.getAction());
        parsedNews.clear();


        log.info("Updating Technology News into CSV File");
        TechnologyNewsGenerator technologyNewsGenerator = new TechnologyNewsGenerator();
        parsedNews = technologyNewsGenerator.getAllTechnologyNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.TECHNOLOGY.getAction());

        saveNewsAfterClusteringIntoCSVFile.doClustering();
        log.info("********News has been saved successfully into CSV file*******");
    }
}
