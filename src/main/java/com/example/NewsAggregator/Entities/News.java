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
    @Column(name = "id")
    private Long newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "publishTime")
    private Long time;

    @Column(name = "link")
    private String link;

    @Column(name = "clusterId")
    private Long clusterId;

    @Column(name = "category")
    private NewsCategory newsCategory;
}
