package com.example.NewsAggregator.NewsGenerators;

import com.example.NewsAggregator.GenerateResponse.HttpURLConnection;
import com.example.NewsAggregator.NewsParsers.NewsParser;
import com.example.NewsAggregator.NewsSources.*;
import com.example.NewsAggregator.Responses.Response;
import org.json.JSONArray;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BusinessNewsGenerator {

    public List<Response> getAllBusinessNews(){

        List<Response> responses = new ArrayList<>();
        try {
            JSONArray bbc = HttpURLConnection.sendGET(BBC.BUSINESS.getAction());
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.BUSINESS.getAction());
            JSONArray cnbc = HttpURLConnection.sendGET(CNBC.BUSINESS.getAction());
            JSONArray ndtv = HttpURLConnection.sendGET(NDTV.BUSINESS.getAction());
            JSONArray newYorkTimes = HttpURLConnection.sendGET(NewYorkTimes.BUSINESS.getAction());
            JSONArray theGuardian = HttpURLConnection.sendGET(TheGuardian.BUSINESS.getAction());
            JSONArray theHindu = HttpURLConnection.sendGET(TheHindu.BUSINESS.getAction());
            JSONArray timesOfIndia = HttpURLConnection.sendGET(TimesOfIndia.BUSINESS.getAction());
            JSONArray news18 = HttpURLConnection.sendGET(News18.BUSINESS.getAction());






            getResonse(bbc, responses);
            getResonse(theHindu, responses);
            getResonse(theGuardian, responses);
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

    private void getResonse(JSONArray jsonArray, List<Response> responses) throws ParseException {
        List<Response> responseList = NewsParser.getParsedNews(jsonArray);
        responses.addAll(responseList);
    }
}
