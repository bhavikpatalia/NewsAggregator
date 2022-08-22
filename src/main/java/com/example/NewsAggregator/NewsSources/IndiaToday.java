
package com.example.NewsAggregator.NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum IndiaToday {

    SPORTS ("https://www.indiatoday.in/rss/1206550");

    private String action;

    IndiaToday(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}