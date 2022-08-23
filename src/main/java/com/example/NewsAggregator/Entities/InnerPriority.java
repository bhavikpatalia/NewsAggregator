package com.example.NewsAggregator.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "INNER_PRIORITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InnerPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long priority_id;

    @Column(name = "newsId")
    private Long newsId;

    @Column(name = "priorityValue")
    private Double priority_value;

}
