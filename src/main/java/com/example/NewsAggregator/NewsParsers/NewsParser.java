package com.example.NewsAggregator.NewsParsers;

import com.example.NewsAggregator.Responses.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NewsParser {

    public  static List<Response> getParsedNews(JSONArray jsonArray) throws ParseException {

        List<Response> news = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            Response response = new Response();
            if(object.has("title")) {
                if(object.get("title").toString().isEmpty()){
                    continue;
                }
                response.setTitle(object.get("title").toString());
            }
            if(object.has("description")) response.setDescription(object.get("description").toString().replaceAll("<[^>]*>", ""));
            if(object.has("pubDate")){
                String time = object.get("pubDate").toString();
                response.setPubTime(getTime(time));
            }
            if(object.has("link")) response.setLink(object.get("link").toString());
            news.add(response);
        }
        return news;
    }

    public static Long getTime(String time) throws ParseException {
        Date parsedDate;
        if(time.charAt(10) == 'T'){
            parsedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SS:SS").parse(time);
        }
        else {
            parsedDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse(time);
        }
        return parsedDate.getTime();
    }

}
