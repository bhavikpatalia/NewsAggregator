package com.example.NewsAggregator.Repository;

import com.example.NewsAggregator.Entities.InnerPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnerPriorityRepository extends JpaRepository<InnerPriority, Long> {
}
