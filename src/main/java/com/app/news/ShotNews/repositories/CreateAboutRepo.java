package com.app.news.ShotNews.repositories;

import com.app.news.ShotNews.entities.AppSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateAboutRepo  extends JpaRepository<AppSettings ,Long> {
}
