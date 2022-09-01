package com.example.NewsAggregator.Repository;

import com.example.NewsAggregator.Entities.News;
import com.example.NewsAggregator.Enum.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
//    ArrayList<News> findAllByClusterId(Integer clusterId);

    News findByNewsId(Integer id);

    @Query("select n from News as n where n.newsCategory = ?1 and n.newsId >= ?2 and n.newsId < ?3")
    List<News> findAllByNewsCategoryAndNewsId(NewsCategory newsCategory, Integer min, Integer max);

    @Modifying
    @Query("delete from News as n where n.newsId <= ?1")
    void deleteAllByNewsIds(Integer val);
}
