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
     List<ClusteredNews> findAllByNewsCategory(NewsCategory newsCategory);

     @Modifying
     @Query("delete from ClusteredNews as cn where cn.newsId in :list")
     void deleteAllByNewsIds(@Param("list") List<Integer> list);

     List<ClusteredNews> findAllByClusterRankAndNewsCategory(Integer clusterRank, NewsCategory newsCategory);
}
