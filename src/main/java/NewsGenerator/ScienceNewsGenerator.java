package NewsGenerator;

import GenerateResponse.HttpURLConnection;
import NewsParsers.NewsParser;
import NewsSources.*;
import Responses.Response;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ScienceNewsGenerator {

    public List<Response> getAllHealthNews(){

        List<Response> responses = new ArrayList<>();
        try {
            JSONArray bbc = HttpURLConnection.sendGET(BBC.SCIENCE.getAction());
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.SCIENCE.getAction());
            JSONArray huffPost = HttpURLConnection.sendGET(Huffpost.SCIENCE.getAction());
            JSONArray indianExpress = HttpURLConnection.sendGET(IndianExpress.SCIENCE.getAction());
            JSONArray newYorkTimes = HttpURLConnection.sendGET(NewYorkTimes.SCIENCE.getAction());
            JSONArray theGuardian = HttpURLConnection.sendGET(TheGuardian.SCIENCE.getAction());
            JSONArray theHindu = HttpURLConnection.sendGET(TheHindu.SCIENCE.getAction());
            JSONArray timesOfIndia = HttpURLConnection.sendGET(TimesOfIndia.SCIENCE.getAction());
            JSONArray cnn = HttpURLConnection.sendGET(TheHindu.SCIENCE.getAction());



            getResonse(bbc, responses);
            getResonse(theHindu, responses);
            getResonse(theGuardian, responses);
            getResonse(newYorkTimes, responses);
            getResonse(indianExpress, responses);
            getResonse(hindustanTimes, responses);
            getResonse(timesOfIndia, responses);
            getResonse(cnn, responses);


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
