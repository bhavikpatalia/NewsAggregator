package com.example.NewsAggregator.Scheduler;

import com.example.NewsAggregator.Constants.Constant;
import com.example.NewsAggregator.Service.ClusteredNewsServiceImpl;
import com.example.NewsAggregator.Service.NewsServiceImpl;
import com.example.NewsAggregator.Service.NewsTimeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RefreshDBScheduler {

    @Autowired
    NewsTimeServiceImpl newsTimeService;

    @Autowired
    NewsServiceImpl newsService;

    @Autowired
    ClusteredNewsServiceImpl clusteredNewsService;

    @Scheduled(fixedRate = 40000L, initialDelay = 60000L)
    public void refreshDB(){

        log.info("Deleting rows from News Table");
        newsService.deleteAllByNewsIds(Constant.minNewsIdForApi - 1);

        log.info("Deleting rows from ClusteredNews Table");
        clusteredNewsService.deleteByNewsIds(Constant.minNewsIdForApi - 1);

        log.info("Deleting rows from NewsTime Table");
        newsTimeService.deleteAllByNewsIds(Constant.minNewsIdForApi - 1);

    }
}
