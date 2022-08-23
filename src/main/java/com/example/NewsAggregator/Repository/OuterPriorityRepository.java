package com.example.NewsAggregator.Repository;

import com.example.NewsAggregator.Entities.OuterPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuterPriorityRepository extends JpaRepository<OuterPriority, Long> {
}
