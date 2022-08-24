package com.example.NewsAggregator.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum ClusteredNewsCSVFiles {

    SPORTS("/Users/vivek.me/NewsAggregator/ClusteredSportsNews.csv"),
    TECHNOLOGY("/Users/vivek.me/NewsAggregator/ClusteredTechnologyNews.csv"),
    ENTERTAINMENT("/Users/vivek.me/NewsAggregator/ClusteredEntertainmentNews.csv"),
    HEALTH("/Users/vivek.me/NewsAggregator/ClusteredHealthNews.csv"),
    BUSINESS("/Users/vivek.me/NewsAggregator/ClusteredBusinessNews.csv"),
    SCIENCE("/Users/vivek.me/NewsAggregator/ClusteredScienceNews.csv"),
    AUTO("/Users/vivek.me/NewsAggregator/ClusteredAutoNews.csv");

    private String action;


    ClusteredNewsCSVFiles(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
