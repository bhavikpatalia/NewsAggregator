package com.example.NewsAggregator.Model;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClusteredNewsModel {

    private Integer clusteredNewsId;
    private Integer newsId;
    private Integer clusterRank;
    private Integer newsRankInCluster;

    @JsonGetter(value = "news_id")
    public Integer getNewsId() {
        return newsId;
    }

    @JsonSetter("news_id")
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    @JsonGetter(value = "clusteredNewsId")
    public Integer getClusteredNewsId() {
        return clusteredNewsId;
    }

    @JsonSetter("clusteredNewsId")
    public void setClusteredNewsId(Integer clusteredNewsId) {
        this.clusteredNewsId = clusteredNewsId;
    }

    @JsonGetter(value = "clusterRank")
    public Integer getClusterRank() {
        return clusterRank;
    }

    @JsonSetter("clusterRank")
    public void setClusterRank(Integer clusterRank) {
        this.clusterRank = clusterRank;
    }

    @JsonGetter(value = "newsRankInCluster")
    public Integer getNewsRankInCluster() {
        return newsRankInCluster;
    }

    @JsonSetter("newsRankInCluster")
    public void setNewsRankInCluster(Integer newsRankInCluster) {
        this.newsRankInCluster = newsRankInCluster;
    }
}
