package com.example.NewsAggregator.Repository;

import com.example.NewsAggregator.Entities.ClusteredNews;
import com.example.NewsAggregator.Enum.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClusteredNewsRepository extends JpaRepository<ClusteredNews, Integer> {

     ClusteredNews findAllById(Integer id);

     @Query("select cn from ClusteredNews as cn where cn.newsCategory = ?1 and cn.newsId >= ?2 and cn.newsId < ?3")
     List<ClusteredNews> findAllByNewsCategoryAndNewsId(NewsCategory newsCategory, Integer min, Integer max);

     @Modifying
     @Query("delete from ClusteredNews as cn where cn.newsId <= ?1")
     void deleteAllByNewsIds(Integer min);

     List<ClusteredNews> findAllByClusterRankAndNewsCategory(Integer clusterRank, NewsCategory newsCategory);
}
