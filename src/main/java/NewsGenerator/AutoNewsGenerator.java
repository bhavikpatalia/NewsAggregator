package NewsGenerator;

import GenerateResponse.HttpURLConnection;
import NewsParsers.NewsParser;
import NewsSources.*;
import Responses.Response;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AutoNewsGenerator {

    public List<Response> getAllHealthNews(){

        List<Response> responses = new ArrayList<>();
        try {
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.AUTO.getAction());
            JSONArray cnbc = HttpURLConnection.sendGET(CNBC.AUTO.getAction());
            JSONArray ndtv = HttpURLConnection.sendGET(NDTV.AUTO.getAction());
            JSONArray newYorkTimes = HttpURLConnection.sendGET(NewYorkTimes.AUTO.getAction());
            JSONArray timesOfIndia = HttpURLConnection.sendGET(TimesOfIndia.AUTO.getAction());
            JSONArray news18 = HttpURLConnection.sendGET(News18.AUTO.getAction());



            getResonse(newYorkTimes, responses);
            getResonse(cnbc, responses);
            getResonse(hindustanTimes, responses);
            getResonse(timesOfIndia, responses);
            getResonse(ndtv, responses);
            getResonse(news18, responses);


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
