package com.example.NewsAggregator.Model;

import com.example.NewsAggregator.Enum.NewsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {

    private Long newsId;
    private String title;
    private String description;
    private Long time;
    private String link;
    private Long clusterId;
    private NewsCategory newsCategory;

}
