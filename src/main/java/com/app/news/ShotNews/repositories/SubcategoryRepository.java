package com.app.news.ShotNews.repositories;

import com.app.news.ShotNews.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findBySlug(String slug);
    boolean existsByName(String name);
}
