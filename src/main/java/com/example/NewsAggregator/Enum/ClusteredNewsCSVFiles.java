package com.example.NewsAggregator.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum ClusteredNewsCSVFiles {

    SPORTS("ClusteredSportsNews.csv"),
    TECHNOLOGY("ClusteredTechnologyNews.csv"),
    ENTERTAINMENT("ClusteredEntertainmentNews.csv"),
    HEALTH("ClusteredHealthNews.csv"),
    BUSINESS("ClusteredBusinessNews.csv"),
    SCIENCE("ClusteredScienceNews.csv"),
    AUTO("ClusteredAutoNews.csv");

    private String action;


    ClusteredNewsCSVFiles(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
