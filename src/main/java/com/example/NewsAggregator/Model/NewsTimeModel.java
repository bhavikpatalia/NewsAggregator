package com.example.NewsAggregator.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsTimeModel {

    private Integer id;
    private Integer news_id;
    private Integer newsPubTime;
}
