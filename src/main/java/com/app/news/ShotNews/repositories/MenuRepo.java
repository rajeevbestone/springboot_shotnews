package com.app.news.ShotNews.repositories;

import com.app.news.ShotNews.entities.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<MenuCategory, Long> {
}
