package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Entities.News;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Repository.NewsRepository;
import com.example.NewsAggregator.Responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl  implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News saveNews(Response response, NewsCategory category) {

        return newsRepository.save(News.builder().title(response.getTitle())
                .link(response.getLink())
                .description(response.getDescription())
                .time(response.getPubTime())
                .newsCategory(category)
                .build());

    }

    @Override
    public List<Response> fetchNewsList() {

        return newsRepository.findAll().stream().map(news -> {
            Response response;
            response = Response.builder().title(news.getTitle())
                    .link(news.getLink())
                    .description(news.getDescription())
                    .pubTime(news.getTime())
                    .build();
            return response;
        }).collect(Collectors.toList());

    }

    @Override
    public void deleteNewsById(Long newsId) {
        newsRepository.deleteById(newsId);
    }

    @Override
    public void deleteAll() {
        newsRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Response> responses, NewsCategory category) {

        newsRepository.saveAll(responses.stream().map(response -> News.builder().title(response.getTitle())
                .link(response.getLink())
                .description(response.getDescription())
                .time(response.getPubTime())
                .newsCategory(category)
                .build()).collect(Collectors.toList()));

    }
}