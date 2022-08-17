package NewsGenerator;

import GenerateResponse.HttpURLConnection;
import NewsParsers.NewsParser;
import NewsSources.*;
import Responses.Response;
import org.json.JSONArray;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SportsNewsGenerator {

    public List<Response> getAllSportsNews(){

        List<Response> responses = new ArrayList<>();
        try {
            JSONArray cnn = HttpURLConnection.sendGET(CNN.SPORTS.getAction());
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.SPORTS.getAction());
            JSONArray huffPost = HttpURLConnection.sendGET(Huffpost.SPORTS.getAction());
            JSONArray indianExpress = HttpURLConnection.sendGET(IndianExpress.SPORTS.getAction());
            JSONArray indiaToday = HttpURLConnection.sendGET(IndiaToday.SPORTS.getAction());
            JSONArray ndtv = HttpURLConnection.sendGET(NDTV.SPORTS.getAction());
            JSONArray news18 = HttpURLConnection.sendGET(News18.SPORTS.getAction());
            JSONArray newYorkTimes = HttpURLConnection.sendGET(NewYorkTimes.SPORTS.getAction());
            JSONArray theGuardian = HttpURLConnection.sendGET(TheGuardian.SPORTS.getAction());
            JSONArray theHindu = HttpURLConnection.sendGET(TheHindu.SPORTS.getAction());
            JSONArray timesOfIndia = HttpURLConnection.sendGET(TimesOfIndia.SPORTS.getAction());

            getResonse(cnn, responses);
            getResonse(timesOfIndia, responses);
            getResonse(theHindu, responses);
            getResonse(theGuardian, responses);
            getResonse(newYorkTimes, responses);
            getResonse(news18, responses);
            getResonse(ndtv, responses);
            getResonse(indiaToday, responses);
            getResonse(indianExpress, responses);
            getResonse(huffPost, responses);
            getResonse(hindustanTimes, responses);

        } catch (Exception e){
           // Will handle it
        }
        return responses;
    }

    private void getResonse(JSONArray jsonArray, List<Response> responses) {
        List<Response> responseList = NewsParser.getParsedNews(jsonArray);
        responses.addAll(responseList);
    }
}
