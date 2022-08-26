package com.example.NewsAggregator.NewsGenerators;

import com.example.NewsAggregator.GenerateResponse.HttpURLConnection;
import com.example.NewsAggregator.NewsParsers.NewsParser;
import com.example.NewsAggregator.NewsSources.*;
import com.example.NewsAggregator.Responses.Response;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HealthNewsGenerator {

    public List<Response> getAllHealthNews(){

        List<Response> responses = new ArrayList<>();
        try {
//            JSONArray bbc = HttpURLConnection.sendGET(BBC.HEALTH.getAction());
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.HEALTH.getAction());
//            JSONArray huffPost = HttpURLConnection.sendGET(Huffpost.HEALTH.getAction());
            JSONArray indianExpress = HttpURLConnection.sendGET(IndianExpress.HEALTH.getAction());
//            JSONArray cnbc = HttpURLConnection.sendGET(CNBC.HEALTH.getAction());
            JSONArray ndtv = HttpURLConnection.sendGET(NDTV.HEALTH.getAction());
//            JSONArray newYorkTimes = HttpURLConnection.sendGET(NewYorkTimes.HEALTH.getAction());
//            JSONArray theGuardian = HttpURLConnection.sendGET(TheGuardian.HEALTH.getAction());
            JSONArray theHindu = HttpURLConnection.sendGET(TheHindu.HEALTH.getAction());

//            getResonse(bbc, responses);
//            getResonse(cnbc, responses);
            getResonse(theHindu, responses);
//            getResonse(theGuardian, responses);
//            getResonse(newYorkTimes, responses);
            getResonse(ndtv, responses);
            getResonse(indianExpress, responses);
//            getResonse(huffPost, responses);
            getResonse(hindustanTimes, responses);

        } catch (Exception e){
            // Will handle it
        }
        return responses;
    }

    private void getResonse(JSONArray jsonArray, List<Response> responses) throws ParseException {
        List<Response> responseList = NewsParser.getParsedNews(jsonArray);
        responses.addAll(responseList);
    }
}
