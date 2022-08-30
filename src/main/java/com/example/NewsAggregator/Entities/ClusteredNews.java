package com.example.NewsAggregator.Entities;

import com.example.NewsAggregator.Enum.NewsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ClusteredNews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClusteredNews {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "cluster_rank")
    private Integer clusterRank;

    @Column(name = "news_rank_in_cluster")
    private Integer newsRank;

    @Column(name = "category")
    private NewsCategory newsCategory;

}
