
import GenerateResponse.HttpURLConnection;
import NewsGenerator.SportsNewsGenerator;
import NewsParsers.NewsParser;
import NewsSources.TheGuardian;
import Responses.Response;
import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class Main {

    NewsParser newsParser = new NewsParser();
    public static void main(String[] args){
        JSONArray response = null;
        try {
            response = HttpURLConnection.sendGET(TheGuardian.SCIENCE.getAction());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main main = new Main();
        SportsNewsGenerator sportsNewsGenerator = new SportsNewsGenerator();
        List<Response> parsedNews = sportsNewsGenerator.getAllSportsNews();
        System.out.println(parsedNews);
    }
}
