package com.example.NewsAggregator.Service;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Entities.NewsTime;
import com.example.NewsAggregator.Model.NewsTimeModel;
import com.example.NewsAggregator.Repository.NewsRepository;
import com.example.NewsAggregator.Repository.NewsTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsTimeServiceImpl implements NewsTimeService{

    @Autowired
    NewsTimeRepository newsTimeRepository;

    @Override
    public void saveAll(Integer startingId, Integer lastId) {
        for(int i = startingId; i < lastId; i++){
            newsTimeRepository.save(NewsTime.builder().id(Constant.count.getAndIncrement())
                    .newsId(i)
                    .systemTime(System.currentTimeMillis())
                    .build());
        }
    }

    public List<Integer> fetchAllNewsIds(){
        return newsTimeRepository.getAllNewsLessThen2Min(System.currentTimeMillis()).stream().map(NewsTime::getNewsId).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllByNewsIds(List<Integer> newsIds){
        if(newsIds.isEmpty())
            return;
        newsTimeRepository.deleteAllByNewsIds(newsIds);
    }
}
