package com.example.NewsAggregator.Repository;

import com.example.NewsAggregator.Entities.NewsTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsTimeRepository extends JpaRepository<NewsTime, Integer> {

    @Query("select nt from NewsTime as nt where (?1 - nt.systemTime) >= 2*60000L")
    List<NewsTime> getAllNewsLessThen2Min(Long curTime);

    @Modifying
    @Query("delete from NewsTime as nt where nt.newsId <= ?1")
    void deleteAllByNewsIds(Integer min);
}
