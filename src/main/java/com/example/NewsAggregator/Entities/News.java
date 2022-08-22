package com.example.NewsAggregator.Entities;

import com.example.NewsAggregator.Enum.NewsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "NEWS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newsId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "PublishTime")
    private Long time;

    @Column(name = "Link")
    private String link;

    @Column(name = "ClusterId")
    private Long clusterId;

    @Column(name = "Category")
    private NewsCategory newsCategory;
}
