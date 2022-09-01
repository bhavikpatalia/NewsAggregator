package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Entities.ClusteredNews;
import com.example.NewsAggregator.Model.ClusteredNewsModel;

import java.util.List;

public interface ClusteredNewsService {

    void saveClusteredNews(ClusteredNewsModel clusteredNewsModel, NewsCategory newsCategory);

    List<ClusteredNews> findAllByNewsCategory(NewsCategory newsCategory);

    void deleteByNewsIds(Integer val);
}
