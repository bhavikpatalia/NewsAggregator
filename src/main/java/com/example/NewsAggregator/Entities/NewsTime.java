package com.example.NewsAggregator.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NewsTime")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsTime {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "news_id")
    private Integer newsId;

    @Column(name = "time")
    private Long systemTime;

}
