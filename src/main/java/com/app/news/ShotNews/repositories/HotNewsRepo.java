package com.app.news.ShotNews.repositories;

import com.app.news.ShotNews.entities.HotNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotNewsRepo extends JpaRepository<HotNews,Long>
{
}
