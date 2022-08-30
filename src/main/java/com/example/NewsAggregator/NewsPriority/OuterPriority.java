package com.example.NewsAggregator.NewsPriority;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.NewsPrefix;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Responses.Response;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OuterPriority {

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

    public static double getAvgNewsPublishDate(List<NewsModel> responses){
        int sumOfDates = responses.stream().mapToInt(response -> getDateOfNewsPublish(response.getTime())).sum();
        return (double) sumOfDates/responses.size();
    }

    public static double getAvgScoreOfNewsSources(List<NewsModel> responses){
        double sumOfDates = responses.stream().mapToDouble(response -> getScoreOfNewsSource(response.getLink())).sum();
        return sumOfDates/responses.size();
    }

    static class Sort implements Comparator<List<NewsModel>>{

        @Override
        public int compare(List<NewsModel> responseList1, List<NewsModel> responseList2) {

            int clusterSize1 = responseList1.size();
            int clusterSize2 = responseList2.size();
            double avgNewsScores1 = getAvgScoreOfNewsSources(responseList1);
            double avgNewsScores2 = getAvgScoreOfNewsSources(responseList2);
            double avgDate1 = getAvgNewsPublishDate(responseList1);
            double avgDate2 = getAvgNewsPublishDate(responseList2);

            if(clusterSize1 > clusterSize2) return -1;
            if(clusterSize1 < clusterSize2) return 1;
            if(avgDate1 > avgDate2) return -1;
            if(avgDate1 < avgDate2) return 1;
            if(avgNewsScores1 > avgNewsScores2) return -1;
            if(avgNewsScores1 == avgNewsScores2)return 0;
            return 1;

        }
    }

    public static void getOuterPrioritizedList(List<List<NewsModel>> responses){
        responses.sort(new Sort());
    }
}
