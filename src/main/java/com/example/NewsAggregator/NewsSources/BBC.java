
package com.example.NewsAggregator.NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum BBC {

    TECHNOLOGY("http://feeds.bbci.co.uk/news/technology/rss.xml"),
    ENTERTAINMENT("http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml"),
    HEALTH("http://feeds.bbci.co.uk/news/health/rss.xml"),
    BUSINESS("http://feeds.bbci.co.uk/news/business/rss.xml"),
    SCIENCE("http://feeds.bbci.co.uk/news/science_and_environment/rss.xml");

    private String action;

    BBC(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}