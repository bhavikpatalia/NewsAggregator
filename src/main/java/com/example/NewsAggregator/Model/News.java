package com.example.NewsAggregator.Model;

import com.example.NewsAggregator.Enum.NewsCategory;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {

    private Long newsId;
    private String title;
    private String description;
    private Long time;
    private String link;
    private String imageURL;
    private Long clusterId;
    private NewsCategory newsCategory;

    @JsonGetter(value = "news_id")
    public Long getNewsId() {
        return newsId;
    }

    @JsonSetter("news_id")
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @JsonGetter(value = "title")
    public String getTitle() {
        return title;
    }

    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter(value = "description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter(value = "time")
    public Long getTime() {
        return time;
    }

    @JsonSetter("time")
    public void setTime(Long time) {
        this.time = time;
    }

    @JsonGetter(value = "link")
    public String getLink() {
        return link;
    }

    @JsonSetter("link")
    public void setLink(String link) {
        this.link = link;
    }

    @JsonGetter(value = "imageURL")
    public String getImageURL() {
        return link;
    }

    @JsonSetter("imageURL")
    public void setImageURL(String link) {
        this.link = link;
    }

    @JsonGetter(value = "cluster_id")
    public Long getClusterId() {
        return clusterId;
    }

    @JsonSetter("cluster_id")
    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    @JsonGetter(value = "newsCategory")
    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    @JsonSetter("newsCategory")
    public void setNewsCategory(NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }
}
