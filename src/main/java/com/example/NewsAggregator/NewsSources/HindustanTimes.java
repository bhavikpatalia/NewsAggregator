package com.example.NewsAggregator.NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum HindustanTimes {

    SPORTS ("https://www.hindustantimes.com/feeds/rss/sports/rssfeed.xml"),
    TECHNOLOGY("https://www.hindustantimes.com/feeds/rss/technology/rssfeed.xml"),
    ENTERTAINMENT("https://www.hindustantimes.com/feeds/rss/entertainment/rssfeed.xml"),
    HEALTH("https://www.hindustantimes.com/feeds/rss/health/rssfeed.xml"),
    BUSINESS("https://www.hindustantimes.com/feeds/rss/business/rssfeed.xml"),
    AUTO("https://www.hindustantimes.com/feeds/rss/car-bike/rssfeed.xml"),
    SCIENCE("https://www.hindustantimes.com/feeds/rss/science/rssfeed.xml");

    private String action;

    HindustanTimes(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}