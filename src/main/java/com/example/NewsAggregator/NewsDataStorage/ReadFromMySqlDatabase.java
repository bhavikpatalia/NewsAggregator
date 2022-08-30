package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Entities.ClusteredNews;
import com.example.NewsAggregator.Entities.News;
import com.example.NewsAggregator.Enum.NewsPrefix;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.UIResponseModel;
import com.example.NewsAggregator.Service.ClusteredNewsServiceImpl;
import com.example.NewsAggregator.Service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReadFromMySqlDatabase {

    @Autowired
    NewsServiceImpl newsService;

    @Autowired
    ClusteredNewsServiceImpl clusteredNewsService;

    public List<NewsModel> readNewsFromDatabase(NewsCategory newsCategory){
        return newsService.findAllByNewsCategory(newsCategory);
    }

    public List<List<UIResponseModel>> readClusteredNewsFromDatabase(NewsCategory newsCategory){
        List<ClusteredNews> allByNewsCategory = clusteredNewsService.findAllByNewsCategory(newsCategory);

        Map<Integer, List<Integer>> cluster = new HashMap<>();

        AtomicInteger index = new AtomicInteger(0);
        for(ClusteredNews clusteredNews : allByNewsCategory){
            if(cluster.containsKey(clusteredNews.getClusterRank())){
                List<Integer> list = cluster.get(clusteredNews.getClusterRank());
                list.add(index.getAndIncrement());
                cluster.put(clusteredNews.getClusterRank(), list);
            }
            else{
                List<Integer> list = new ArrayList<>();
                list.add(index.getAndIncrement());
                cluster.put(clusteredNews.getClusterRank(), list);
            }
        }

        List<List<UIResponseModel>> response = new ArrayList<>();
        getResponseList(allByNewsCategory, cluster, response);

        sortInnerList(response);
        sortOuterList(response);

        return response;
    }

    private void getResponseList(List<ClusteredNews> allByNewsCategory, Map<Integer, List<Integer>> cluster, List<List<UIResponseModel>> response) {
        for(Integer integer : cluster.keySet()){
            List<UIResponseModel> uiResponseModels = new ArrayList<>();
            for(Integer i : cluster.get(integer)){
                int position = i;
                News news = newsService.getNews(allByNewsCategory.get(position).getNewsId());
                uiResponseModels.add(UIResponseModel.builder().clusterRank(allByNewsCategory.get(position).getClusterRank())
                        .newsRankInCluster(allByNewsCategory.get(position).getNewsRank())
                        .description(news.getDescription())
                        .link(news.getLink())
                        .imageURL(news.getImageURL())
                        .title(news.getTitle())
                        .newsSource(getNewsSource(news.getLink()))
                        .time(getTimeDiff(news.getTime()))
                        .build());
            }
            response.add(uiResponseModels);
        }
    }

    public String getTimeDiff(Long newsPubTime) {
        Long curTime = System.currentTimeMillis();
        Long diff = curTime - newsPubTime;
        Long diffSeconds = diff / 1000 % 60;
        Long diffMinutes = diff / (60 * 1000) % 60;
        Long diffHours = diff / (60 * 60 * 1000) % 24;
        Long diffDays = diff / (24 * 60 * 60 * 1000);

        String time = "";
        if(diffDays > 0) time = time + diffDays + " days ";
        else if(diffHours > 0) time = time + diffHours + " hours ";
        else if(diffMinutes > 0) time = time + diffMinutes + " minutes ";
        else if(diffSeconds > 0) time = time + diffSeconds + " seconds ";

        time  = time + "ago";
        return time;
    }

    private String getNewsSource(String newsLink) {
        if(newsLink.contains(NewsPrefix.HINDUSTANTIMES.getAction())) return "Hindustan Times";
        if(newsLink.contains(NewsPrefix.INDIANEXPRESS.getAction())) return "Indian Express";
        if(newsLink.contains(NewsPrefix.INDIATODAY.getAction())) return "India Today";
        if(newsLink.contains(NewsPrefix.NDTV.getAction())) return "Ndtv";
        if(newsLink.contains(NewsPrefix.NEWS18.getAction())) return "News18";
        if(newsLink.contains(NewsPrefix.THEHINDU.getAction())) return "The Hindu";
        return "Times Of India";
    }

    private void sortInnerList(List<List<UIResponseModel>> response) {

        class Sort implements Comparator<UIResponseModel> {

            @Override
            public int compare(UIResponseModel response1, UIResponseModel response2) {
                return response1.getNewsRankInCluster().compareTo(response2.getNewsRankInCluster());
            }
        }
        for(List<UIResponseModel> uiResponseModels : response){
            uiResponseModels.sort(new Sort());
        }
    }

    private void sortOuterList(List<List<UIResponseModel>> response) {

        class Sort implements Comparator<List<UIResponseModel>> {

            @Override
            public int compare(List<UIResponseModel> o1, List<UIResponseModel> o2) {

                if(o1.size() > o2.size()) return -1;
                if(o1.size() == o2.size()) return 0;
                return 1;
            }
        }
        response.sort(new Sort());

    }
}
