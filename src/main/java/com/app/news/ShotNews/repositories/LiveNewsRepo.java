package com.app.news.ShotNews.repositories;

import com.app.news.ShotNews.entities.LiveNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveNewsRepo extends JpaRepository<LiveNews,Long>
{
}
