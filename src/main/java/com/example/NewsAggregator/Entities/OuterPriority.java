package com.example.NewsAggregator.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OUTER_PRIORITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OuterPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long priority_id;

    @Column(name = "clusterId")
    private Long cluster_id;

    @Column(name = "priorityValue")
    private Double priority_value;

}

