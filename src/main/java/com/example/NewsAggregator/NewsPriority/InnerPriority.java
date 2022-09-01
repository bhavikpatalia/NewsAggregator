package com.example.NewsAggregator.NewsPriority;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.NewsPrefix;
import com.example.NewsAggregator.Model.NewsModel;
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

    public static Long getDateOfNewsPublish(Long pubTime){
        return ((System.currentTimeMillis() - pubTime)/(60 * 60 * 1000));
    }

    public static double getDayScore(Long hour){
        if(hour > 100) return 0L;
        else return (double) (100 - hour)/100;
    }

    static class Sort implements Comparator<NewsModel> {

        @Override
        public int compare(NewsModel response1, NewsModel response2) {
            double newsScore1 = getScoreOfNewsSource(response1.getLink())/Constant.maxNewsSourceScore;
            double newsScore2 = getScoreOfNewsSource(response2.getLink())/Constant.maxNewsSourceScore;
            double hours1 = getDayScore(getDateOfNewsPublish(response1.getTime()));
            double hours2 = getDayScore(getDateOfNewsPublish(response2.getTime()));

            double score1 = newsScore1*0.5 + hours1*1.5;
            double score2 = newsScore2*0.5 + hours2*1.5;
            return Double.compare(score2, score1);
        }
    }

    public static void getInnerPrioritizedList(List<List<NewsModel>> responses){
        for(List<NewsModel> responseList : responses){
            responseList.sort(new Sort());
        }
    }
}