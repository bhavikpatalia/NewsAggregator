package com.example.NewsAggregator.Entities;

import com.example.NewsAggregator.Enum.NewsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "News")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @Column(name = "id")
    private Integer newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private Long time;

    @Column(name = "link")
    private String link;

    @Column(name = "category")
    private NewsCategory newsCategory;

    @Column(name = "imageURL")
    private String imageURL;
}
