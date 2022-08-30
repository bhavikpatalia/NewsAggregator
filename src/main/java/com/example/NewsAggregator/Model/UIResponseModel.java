package com.example.NewsAggregator.Model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UIResponseModel {

    private String link;
    private String title;
    private String description;
    private Integer clusterRank;
    private Integer newsRankInCluster;
    private String imageURL;
    private String time;
    private String newsSource;
}
