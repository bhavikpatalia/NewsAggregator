package com.example.NewsAggregator.Enum;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum NewsCategory {
    AUTO("AUTO"),
    BUSINESS("BUSINESS"),
    ENTERTAINMENT("ENTERTAINMENT"),
    SPORTS("SPORTS"),
    SCIENCE("SCIENCE"),
    TECHNOLOGY("TECHNOLOGY"),
    HEALTH("HEALTH");

    private String action;


    NewsCategory(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
