package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Entities.News;
import com.example.NewsAggregator.Enum.NewsCategory;
import com.example.NewsAggregator.Model.NewsModel;
import com.example.NewsAggregator.Repository.NewsRepository;
import com.example.NewsAggregator.Responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl  implements NewsService{

    @Autowired
    NewsRepository newsRepository;

    @Override
    public void saveNews(Response response, NewsCategory category) {

        newsRepository.save(News.builder().title(response.getTitle())
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
    public void deleteNewsById(Integer newsId) {
        newsRepository.deleteById(newsId);
    }

    @Override
    public void deleteAll() {
        newsRepository.deleteAll();
    }

    @Override
    public void saveAll(List<Response> responses, NewsCategory category) {

        newsRepository.saveAll(responses.stream().map(response -> News.builder().title(response.getTitle())
                .newsId(Constant.countWriteNewsDB.getAndIncrement())
                .link(response.getLink())
                .description(response.getDescription())
                .time(response.getPubTime())
                .imageURL(response.getImageUrl())
                .newsCategory(category)
                .build()).collect(Collectors.toList()));

    }

    @Override
    public List<NewsModel> findAllByNewsCategory(NewsCategory newsCategory) {
        return newsRepository.findAllByNewsCategory(newsCategory).stream().map(news -> (
                NewsModel.builder().newsId(news.getNewsId())
                .link(news.getLink())
                .description(news.getDescription())
                .newsCategory(newsCategory)
                .imageURL(news.getImageURL())
                .time(news.getTime())
                .title(news.getTitle())
                .build()
                )).collect(Collectors.toList());
    }

    public News getNews(int id){
        return newsRepository.findByNewsId(id);
    }

    @Transactional
    public void deleteAllByNewsIds(List<Integer> newsIds){
        if(newsIds.isEmpty()) return;
        newsRepository.deleteAllByNewsIds(newsIds);
    }
}
