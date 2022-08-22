
package com.example.NewsAggregator.NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum TheGuardian {

    SPORTS ("https://www.theguardian.com/uk/sport/rss"),
    TECHNOLOGY("https://www.theguardian.com/uk/technology/rss"),
    ENTERTAINMENT("https://www.theguardian.com/music/rss"),
    HEALTH("https://www.theguardian.com/lifeandstyle/health-and-wellbeing/rss"),
    BUSINESS("https://www.theguardian.com/uk/business/rss"),
    SCIENCE("https://www.theguardian.com/science/rss");

    private String action;

    TheGuardian(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}