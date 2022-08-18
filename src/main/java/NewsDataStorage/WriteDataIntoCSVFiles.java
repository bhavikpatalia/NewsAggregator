package NewsDataStorage;

import NewsGenerator.*;
import Responses.Response;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteDataIntoCSVFiles {
    public static void main(String[] args){
        ScienceNewsGenerator scienceNewsGenerator = new ScienceNewsGenerator();
        List<Response> parsedNews = scienceNewsGenerator.getAllScienceNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Science.csv");
        parsedNews.clear();
        AutoNewsGenerator autoNewsGenerator = new AutoNewsGenerator();
        parsedNews = autoNewsGenerator.getAllAutoNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Auto.csv");
        parsedNews.clear();
        BusinessNewsGenerator businessNewsGenerator = new BusinessNewsGenerator();
        parsedNews = businessNewsGenerator.getAllBusinessNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Business.csv");
        parsedNews.clear();
        EntertainmentNewsGenerator entertainmentNewsGenerator = new EntertainmentNewsGenerator();
        parsedNews = entertainmentNewsGenerator.getAllEntertainmentNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Entertainment.csv");
        parsedNews.clear();
        HealthNewsGenerator healthNewsGenerator = new HealthNewsGenerator();
        parsedNews = healthNewsGenerator.getAllHealthNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Health.csv");
        parsedNews.clear();
        SportsNewsGenerator sportsNewsGenerator = new SportsNewsGenerator();
        parsedNews = sportsNewsGenerator.getAllSportsNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Sports.csv");
        parsedNews.clear();
        TechnologyNewsGenerator technologyNewsGenerator = new TechnologyNewsGenerator();
        parsedNews = technologyNewsGenerator.getAllTechnologyNews();
        writeDataForCustomSeparatorCSV(parsedNews, "/Users/vivek.me/NewsAggregator/Technology.csv");
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
