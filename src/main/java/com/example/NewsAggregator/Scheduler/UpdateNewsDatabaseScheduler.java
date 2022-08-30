package com.example.NewsAggregator.Scheduler;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.CSVFiles;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.NewsDataStorage.SaveNewsAfterClusteringIntoCSVFile;
import com.example.NewsAggregator.NewsDataStorage.WriteDataIntoMySql;
import com.example.NewsAggregator.NewsGenerators.*;
import com.example.NewsAggregator.Responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.NewsAggregator.NewsDataStorage.WriteDataIntoCSVFile.writeDataForCustomSeparatorCSV;


@Slf4j
@Service
public class UpdateNewsDatabaseScheduler {


    @Autowired
    WriteDataIntoMySql writeDataIntoMySql;

    @Autowired
    SaveNewsAfterClusteringIntoCSVFile saveNewsAfterClusteringIntoCSVFile;

    @Scheduled(fixedRate = 2*60000L, initialDelay = 0L)
    public void updateNewsDatabase() throws IOException {

        log.info("Updating Science News into CSV File");
        ScienceNewsGenerator scienceNewsGenerator = new ScienceNewsGenerator();
        List<Response> parsedNews = scienceNewsGenerator.getAllScienceNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.SCIENCE.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.SCIENCE);
        parsedNews.clear();


        log.info("Updating Auto News into CSV File");
        AutoNewsGenerator autoNewsGenerator = new AutoNewsGenerator();
        parsedNews = autoNewsGenerator.getAllAutoNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.AUTO.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.AUTO);
        parsedNews.clear();


        log.info("Updating Business News into CSV File");
        BusinessNewsGenerator businessNewsGenerator = new BusinessNewsGenerator();
        parsedNews = businessNewsGenerator.getAllBusinessNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.BUSINESS.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.BUSINESS);
        parsedNews.clear();


        log.info("Updating Entertainment News into CSV File");
        EntertainmentNewsGenerator entertainmentNewsGenerator = new EntertainmentNewsGenerator();
        parsedNews = entertainmentNewsGenerator.getAllEntertainmentNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.ENTERTAINMENT.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.ENTERTAINMENT);
        parsedNews.clear();


        log.info("Updating Health News into CSV File");
        HealthNewsGenerator healthNewsGenerator = new HealthNewsGenerator();
        parsedNews = healthNewsGenerator.getAllHealthNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.HEALTH.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.HEALTH);
        parsedNews.clear();


        log.info("Updating Sports News into CSV File");
        SportsNewsGenerator sportsNewsGenerator = new SportsNewsGenerator();
        parsedNews = sportsNewsGenerator.getAllSportsNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.SPORTS.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.SPORTS);
        parsedNews.clear();


        log.info("Updating Technology News into CSV File");
        TechnologyNewsGenerator technologyNewsGenerator = new TechnologyNewsGenerator();
        parsedNews = technologyNewsGenerator.getAllTechnologyNews();
        writeDataForCustomSeparatorCSV(parsedNews, CSVFiles.TECHNOLOGY.getAction());
        writeDataIntoMySql.writeParsedNewsIntoDB(parsedNews, NewsCategory.TECHNOLOGY);

        saveNewsAfterClusteringIntoCSVFile.doClustering();
        log.info("********News has been saved successfully into CSV file*******");
//        Constant.count.set(1);
//        Constant.countWriteNewsDB.set(1);
//        Constant.countWriteClusteredNewsDB.set(1);
    }
}
