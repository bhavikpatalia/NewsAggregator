
import GenerateResponse.HttpURLConnection;
import NewsGenerator.*;
import NewsParsers.NewsParser;
import NewsSources.*;
import Responses.Response;
import org.json.JSONArray;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParseException {
        JSONArray response = null;
        try {
            response = HttpURLConnection.sendGET(TheHindu.SCIENCE.getAction());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main main = new Main();
        ScienceNewsGenerator scienceNewsGenerator = new ScienceNewsGenerator();
//        List<Response> parsedNews = NewsParser.getParsedNews(response);
        List<Response> parsedNews = scienceNewsGenerator.getAllScienceNews();
        System.out.println(parsedNews);
    }
}
