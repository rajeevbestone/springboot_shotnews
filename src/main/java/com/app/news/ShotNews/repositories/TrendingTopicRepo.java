package com.app.news.ShotNews.repositories;

import com.app.news.ShotNews.entities.TrendingTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendingTopicRepo extends JpaRepository<TrendingTopic,Long> {
}
