package com.example.NewsAggregator.Enum;

import lombok.Data;
import lombok.Getter;

@Getter
public enum NewsPrefix {

    INDIANEXPRESS("indianexpress"),
    NEWS18("news18"),
    TIMESOFINDIA("timesofindia"),
    NDTV("ndtv"),
    INDIATODAY("indiatoday"),
    HINDUSTANTIMES("hindustantimes"),
    THEHINDU("thehindu");

    private String action;


    NewsPrefix(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
