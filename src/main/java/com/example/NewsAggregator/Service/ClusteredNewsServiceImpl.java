package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Entities.ClusteredNews;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.ClusteredNewsModel;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Repository.ClusteredNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClusteredNewsServiceImpl implements ClusteredNewsService{

    @Autowired
    ClusteredNewsRepository clusteredNewsRepository;


    @Override
    public void saveClusteredNews(ClusteredNewsModel clusteredNewsModel, NewsCategory newsCategory) {
        clusteredNewsRepository.save(ClusteredNews.builder().id(clusteredNewsModel.getClusteredNewsId())
                                     .newsId(clusteredNewsModel.getNewsId())
                                     .clusterRank(clusteredNewsModel.getClusterRank())
                                     .newsRank(clusteredNewsModel.getNewsRankInCluster())
                                     .newsCategory(newsCategory)
                                     .build());
    }

    public void deleteAll(){
        clusteredNewsRepository.deleteAll();
    }

    public List<ClusteredNews> findAllByNewsCategory(NewsCategory newsCategory){
        return clusteredNewsRepository.findAllByNewsCategory(newsCategory);
    }

    @Transactional
    public void deleteByNewsIds(List<Integer> newsIds){
        if(newsIds.isEmpty()) return;
        clusteredNewsRepository.deleteAllByNewsIds(newsIds);
    }
}
