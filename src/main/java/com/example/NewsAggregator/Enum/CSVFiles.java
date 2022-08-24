package com.example.NewsAggregator.Enum;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum CSVFiles {

    SPORTS("Sports.csv"),
    TECHNOLOGY("Technology.csv"),
    ENTERTAINMENT("Entertainment.csv"),
    HEALTH("Health.csv"),
    BUSINESS("Business.csv"),
    SCIENCE("Science.csv"),
    AUTO("Auto.csv");

    private String action;


    CSVFiles(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
