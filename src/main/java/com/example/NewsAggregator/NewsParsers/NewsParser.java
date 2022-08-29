package com.example.NewsAggregator.NewsParsers;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.NewsPrefix;
import com.example.NewsAggregator.Responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
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
            addImageURL(response, object);
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

    public static void addImageURL(Response response, JSONObject object){

        try {
            if (response.getLink().contains(NewsPrefix.NDTV.getAction())) {
                response.setImageUrl(object.get("fullimage").toString().split("\\?")[0]);
            }
            if (response.getLink().contains(NewsPrefix.HINDUSTANTIMES.getAction())
                    || response.getLink().contains(NewsPrefix.NEWS18.getAction())) {
                response.setImageUrl(object.getJSONObject("media:content").get("url").toString());
            }
            if (response.getLink().contains(NewsPrefix.THEHINDU.getAction())
                    || response.getLink().contains(NewsPrefix.TIMESOFINDIA.getAction())) {
                response.setImageUrl(Constant.defaultImgURL);
            }
            if(response.getLink().contains(NewsPrefix.INDIANEXPRESS.getAction())){
                response.setImageUrl(object.getJSONObject("media:thumbnail").get("url").toString());
            }
            if(response.getImageUrl() == null) response.setImageUrl(Constant.defaultImgURL);
        }catch (Exception e){
            response.setImageUrl(Constant.defaultImgURL);
            log.error(e.toString());
        }
    }

}

