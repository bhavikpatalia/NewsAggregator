package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.NewsGenerator.*;
import com.example.NewsAggregator.Responses.Response;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class WriteDataIntoCSVFiles {


    @Scheduled(fixedDelay = 20000L, initialDelay = 0L)
    public void writeDataIntoCSVFiles(){

        log.info("Updating Science News into CSV File");
        ScienceNewsGenerator scienceNewsGenerator = new ScienceNewsGenerator();
        List<Response> parsedNews = scienceNewsGenerator.getAllScienceNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Science.csv");
        parsedNews.clear();


        log.info("Updating Auto News into CSV File");
        AutoNewsGenerator autoNewsGenerator = new AutoNewsGenerator();
        parsedNews = autoNewsGenerator.getAllAutoNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Auto.csv");
        parsedNews.clear();


        log.info("Updating Business News into CSV File");
        BusinessNewsGenerator businessNewsGenerator = new BusinessNewsGenerator();
        parsedNews = businessNewsGenerator.getAllBusinessNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Business.csv");
        parsedNews.clear();


        log.info("Updating Entertainment News into CSV File");
        EntertainmentNewsGenerator entertainmentNewsGenerator = new EntertainmentNewsGenerator();
        parsedNews = entertainmentNewsGenerator.getAllEntertainmentNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Entertainment.csv");
        parsedNews.clear();


        log.info("Updating Health News into CSV File");
        HealthNewsGenerator healthNewsGenerator = new HealthNewsGenerator();
        parsedNews = healthNewsGenerator.getAllHealthNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Health.csv");
        parsedNews.clear();


        log.info("Updating Sports News into CSV File");
        SportsNewsGenerator sportsNewsGenerator = new SportsNewsGenerator();
        parsedNews = sportsNewsGenerator.getAllSportsNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Sports.csv");
        parsedNews.clear();


        log.info("Updating Technology News into CSV File");
        TechnologyNewsGenerator technologyNewsGenerator = new TechnologyNewsGenerator();
        parsedNews = technologyNewsGenerator.getAllTechnologyNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Technology.csv");

        // Similarity Algo would be called from here
    }
    public static void writeDataForCustomSeparatorCSV(List<Response> responses, String path)
    {
        File file = new File(path);

        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Id", "Title", "Time", "Description", "Link" });
            int count = 1;
            for(Response response : responses){
                String[] temp = new String[]{Integer.toString(count), response.getTitle(), response.getPubTime().toString(), response.getDescription(), response.getLink()};
                data.add(temp);
                count += 1;
            }
            writer.writeAll(data);

            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
