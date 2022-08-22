
package com.example.NewsAggregator.NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum CNN {

    SPORTS ("http://rss.cnn.com/rss/edition_sport.rss"),
    TECHNOLOGY("http://rss.cnn.com/rss/edition_technology.rss"),
    ENTERTAINMENT("http://rss.cnn.com/rss/edition_entertainment.rss"),
    SCIENCE("http://rss.cnn.com/rss/edition_space.rss");

    private String action;

    CNN(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}