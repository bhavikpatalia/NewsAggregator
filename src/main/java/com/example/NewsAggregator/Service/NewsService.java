package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Entities.News;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Responses.Response;

import java.util.List;

public interface NewsService {

    void saveNews(Response response, NewsCategory category);

    List<Response> fetchNewsList();

    void deleteNewsById(Integer newsId);

    void deleteAll();

    void saveAll(List<Response> responses, NewsCategory category);

    List<NewsModel> findAllByNewsCategory(NewsCategory newsCategory);

    void deleteAllByNewsIds(List<Integer> newsIds);
}
