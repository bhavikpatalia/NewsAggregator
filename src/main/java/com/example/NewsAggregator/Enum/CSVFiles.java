package com.example.NewsAggregator.Enum;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum CSVFiles {

    SPORTS("/Users/vivek.me/NewsAggregator/Sports.csv"),
    TECHNOLOGY("/Users/vivek.me/NewsAggregator/Technology.csv"),
    ENTERTAINMENT("/Users/vivek.me/NewsAggregator/Entertainment.csv"),
    HEALTH("/Users/vivek.me/NewsAggregator/Health.csv"),
    BUSINESS("/Users/vivek.me/NewsAggregator/Business.csv"),
    SCIENCE("/Users/vivek.me/NewsAggregator/Science.csv"),
    AUTO("/Users/vivek.me/NewsAggregator/Auto.csv");

    private String action;


    CSVFiles(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
