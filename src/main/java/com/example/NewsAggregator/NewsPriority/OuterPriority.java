package com.example.NewsAggregator.NewsPriority;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.NewsPrefix;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Responses.Response;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OuterPriority {

    static Integer maxClusterSize = 1;
    static Integer zero = 0;
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

//    public static int getDateOfNewsPublish(Long pubTime){
//        return Integer.parseInt(new Date(pubTime).toString().split(" ")[2]);
//    }

//    public static double getNewsPublishDate(List<NewsModel> responses){
//        return responses.stream().mapToInt(response -> getDateOfNewsPublish(response.getTime())).sum();
//    }

    public static double getScoreOfNewsSources(List<NewsModel> responses){
        return responses.stream().mapToDouble(response -> getScoreOfNewsSource(response.getLink())).sum();
    }

    static class Sort implements Comparator<List<NewsModel>>{

        @Override
        public int compare(List<NewsModel> responseList1, List<NewsModel> responseList2) {

//            double clusterSize1 = (double) responseList1.size()/maxClusterSize;
//            double clusterSize2 = (double) responseList2.size()/maxClusterSize;
//            double allNewsScoresSum1 = getScoreOfNewsSources(responseList1)/Constant.maxNewsSourceScore;
//            double allNewsScoresSum2 = getScoreOfNewsSources(responseList2)/Constant.maxNewsSourceScore;
//            double allPubDateSum1 = getNewsPublishDate(responseList1)/Constant.maxNewsPubDate;
//            double allPubDateSum2 = getNewsPublishDate(responseList2)/Constant.maxNewsPubDate;

            double clusterSize1 =  Math.log(responseList1.size())/Math.log(maxClusterSize);
            double clusterSize2 =  Math.log(responseList2.size())/Math.log(maxClusterSize);
            double allNewsScoresSum1 = getScoreOfNewsSources(responseList1)/(responseList1.size() * Constant.maxNewsSourceScore);
            double allNewsScoresSum2 = getScoreOfNewsSources(responseList2)/(responseList2.size() * Constant.maxNewsSourceScore);
            double allPubDateSum1 = getNewsPublishDate(responseList1);
            double allPubDateSum2 = getNewsPublishDate(responseList2);

            double score1 = clusterSize1*1.5 + allNewsScoresSum1*0.2 + allPubDateSum1;
            double score2 = clusterSize2*1.5 + allNewsScoresSum2*0.2 + allPubDateSum2;
            return Double.compare(score2, score1);
        }
    }

    public static void getOuterPrioritizedList(List<List<NewsModel>> responses){
        for(List<NewsModel> newsModelList : responses) maxClusterSize = Math.max(maxClusterSize, newsModelList.size());
        responses.sort(new Sort());
    }
    public static Long getDateOfNewsPublish(Long pubTime){
        return (System.currentTimeMillis() - pubTime)/(24 * 60 * 60 * 1000);
    }

    public static double getDayScore(Long day){
        if(day > 5) return 0L;
        else return (double) (5 - day)/5;
    }
    //
    public static double getNewsPublishDate(List<NewsModel> responses){
        return (responses.stream().mapToDouble(response -> getDayScore(getDateOfNewsPublish(response.getTime()))).sum())/responses.size();
    }
}

//    double clusterSize1 = (double) responseList1.size()/maxClusterSize;
//    double clusterSize2 = (double) responseList2.size()/maxClusterSize;
//    double allNewsScoresSum1 = getScoreOfNewsSources(responseList1)/responseList1.size() * Constant.maxNewsSourceScore;
//    double allNewsScoresSum2 = getScoreOfNewsSources(responseList2)/responseList2.size() * Constant.maxNewsSourceScore;
//    double allPubDateSum1 = getNewsPublishDate(responseList1);
//    double allPubDateSum2 = getNewsPublishDate(responseList2);
//
//    double score1 = clusterSize1*1.2 + allNewsScoresSum1*0.9 + allPubDateSum1*1.1;
//    double score2 = clusterSize2*1.2 + allNewsScoresSum2*0.9 + allPubDateSum2*1.1;
//            return Double.compare(score2, score1);
//    public static Long getDateOfNewsPublish(Long pubTime){
//        return (System.currentTimeMillis() - pubTime)/(24 * 60 * 60 * 1000);
//}
//
//    public static double getDayScore(Long day){
//        if(day > 5) return 0L;
//        else return (double) (5 - day)/5;
//    }
////
//    public static double getNewsPublishDate(List<NewsModel> responses){
//        return (responses.stream().mapToDouble(response -> getDayScore(getDateOfNewsPublish(response.getTime()))).sum())/responses.size();
//    }