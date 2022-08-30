package com.example.NewsAggregator.NewsDataStorage;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.ClusteredNewsModel;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Responses.Response;
import com.example.NewsAggregator.Service.ClusteredNewsServiceImpl;
import com.example.NewsAggregator.Service.NewsServiceImpl;
import com.example.NewsAggregator.Service.NewsTimeService;
import com.example.NewsAggregator.Service.NewsTimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WriteDataIntoMySql {

    @Autowired
    NewsServiceImpl newsService;

    @Autowired
    ClusteredNewsServiceImpl clusteredNewsService;

    @Autowired
    NewsTimeServiceImpl newsTimeService;

    public void writeParsedNewsIntoDB(List<Response> responses, NewsCategory newsCategory){
        int initial = Constant.countWriteNewsDB.get();
        newsService.saveAll(responses, newsCategory);
        newsTimeService.saveAll(initial, Constant.countWriteNewsDB.get());

    }

    public void writeClusteredNewsIntoDB(List<List<NewsModel>> responses, NewsCategory newsCategory){
        AtomicInteger clusterRank = new AtomicInteger(1);

        if(Constant.countWriteClusteredNewsDB.get() == 1){
            clusteredNewsService.deleteAll();
        }

        for(List<NewsModel> responseList : responses){
            AtomicInteger newsRankInCluster = new AtomicInteger(1);
            for(NewsModel response : responseList){
                clusteredNewsService.saveClusteredNews(ClusteredNewsModel.builder()
                .clusteredNewsId(Constant.countWriteClusteredNewsDB.getAndIncrement())
                .newsId(response.getNewsId())
                .clusterRank(clusterRank.get())
                .newsRankInCluster(newsRankInCluster.getAndIncrement())
                .build(), newsCategory);
            }
            clusterRank.getAndIncrement();
        }
    }
}
