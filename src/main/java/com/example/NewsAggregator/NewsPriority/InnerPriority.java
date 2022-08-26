package com.example.NewsAggregator.NewsPriority;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.NewsPrefix;
import com.example.NewsAggregator.Responses.Response;
import java.util.*;

public class InnerPriority {

    public static double getScoreOfNewsSource(String newsLink){

        if(newsLink.contains(NewsPrefix.HINDUSTANTIMES.getAction())) return Constant.hindustanTimes;
        if(newsLink.contains(NewsPrefix.INDIANEXPRESS.getAction())) return Constant.indianExpress;
        if(newsLink.contains(NewsPrefix.INDIATODAY.getAction())) return Constant.indiaToday;
        if(newsLink.contains(NewsPrefix.NDTV.getAction())) return Constant.ndtv;
        if(newsLink.contains(NewsPrefix.NEWS18.getAction())) return Constant.news18;
        if(newsLink.contains(NewsPrefix.THEHINDU.getAction())) return Constant.theHindu;
        if(newsLink.contains(NewsPrefix.TIMESOFINDIA.getAction())) return Constant.timeOfIndia;
        return 0.0d;
    }

    public static int getDateOfNewsPublish(Long pubTime){
        return Integer.parseInt(new Date(pubTime).toString().split(" ")[2]);
    }

    static class Sort implements Comparator<Response> {

        @Override
        public int compare(Response response1, Response response2) {
            double newsScore1 = getScoreOfNewsSource(response1.getLink());
            double newsScore2 = getScoreOfNewsSource(response2.getLink());
            int date1 = getDateOfNewsPublish(response1.getPubTime());
            int date2 = getDateOfNewsPublish(response2.getPubTime());

            if(date1 > date2) return -1;
            if(date1 < date2) return 1;
            if(newsScore1 > newsScore2) return -1;
            if(newsScore1 == newsScore2) return 0;
            return 1;

        }
    }

    public static void getInnerPrioritizedList(List<List<Response>> responses){
        for(List<Response> responseList : responses){
            responseList.sort(new Sort());
        }
    }
}
