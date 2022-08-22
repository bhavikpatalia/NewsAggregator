
package com.example.NewsAggregator.NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum News18 {

    SPORTS ("https://www.news18.com/rss/sports.xml"),
    TECHNOLOGY("https://www.news18.com/rss/tech.xml"),
    BUSINESS("https://www.news18.com/rss/business.xml"),
    AUTO("https://www.news18.com/rss/auto.xml");
    private String action;

    News18(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}