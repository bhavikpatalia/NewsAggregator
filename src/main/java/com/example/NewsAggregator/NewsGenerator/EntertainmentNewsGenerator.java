package com.example.NewsAggregator.NewsGenerator;

import com.example.NewsAggregator.GenerateResponse.HttpURLConnection;
import com.example.NewsAggregator.NewsParsers.NewsParser;
import com.example.NewsAggregator.NewsSources.*;
import com.example.NewsAggregator.Responses.Response;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EntertainmentNewsGenerator {

    public List<Response> getAllEntertainmentNews(){

        List<Response> responses = new ArrayList<>();
        try {
            JSONArray cnn = HttpURLConnection.sendGET(CNN.ENTERTAINMENT.getAction());
            JSONArray bbc = HttpURLConnection.sendGET(BBC.ENTERTAINMENT.getAction());
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.ENTERTAINMENT.getAction());
            JSONArray huffPost = HttpURLConnection.sendGET(Huffpost.ENTERTAINMENT.getAction());
            JSONArray indianExpress = HttpURLConnection.sendGET(IndianExpress.ENTERTAINMENT.getAction());
            JSONArray ndtv = HttpURLConnection.sendGET(NDTV.ENTERTAINMENT.getAction());
            JSONArray theGuardian = HttpURLConnection.sendGET(TheGuardian.ENTERTAINMENT.getAction());
            JSONArray theHindu = HttpURLConnection.sendGET(TheHindu.ENTERTAINMENT.getAction());
            JSONArray timesOfIndia = HttpURLConnection.sendGET(TimesOfIndia.ENTERTAINMENT.getAction());

            getResonse(cnn, responses);
            getResonse(timesOfIndia, responses);
            getResonse(theHindu, responses);
            getResonse(theGuardian, responses);
            getResonse(bbc, responses);
            getResonse(ndtv, responses);
            getResonse(indianExpress, responses);
            getResonse(huffPost, responses);
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
