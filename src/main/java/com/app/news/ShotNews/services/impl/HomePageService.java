package com.app.news.ShotNews.services.impl;

import com.app.news.ShotNews.entities.Category;
import com.app.news.ShotNews.entities.Post;
import com.app.news.ShotNews.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageService {

    @Autowired
    private PostRepository postRepository;

    // Fetch Hot News (for a specific subcategory)
    public List<Post> getHotNews() {
        return postRepository.findByIsHotTrueOrderByCreatedAtDesc();
    }

    // Fetch Slider News (featured news)
    public List<Post> getSliderNews() {
        return postRepository.findByIsSliderTrueOrderByCreatedAtDesc();
    }

    // Fetch Live News (news marked as live)
    public List<Post> getLiveNews() {
        return postRepository.findByIsLiveTrueOrderByCreatedAtDesc();
    }

    // Fetch Ground Level News (from a specific subcategory, e.g., "ground-news")
    public List<Post> getGroundLevelNews()
    {
        return postRepository.findBySubcategorySlugOrderByCreatedAtDesc("hand-picked-news");
    }

    // Fetch Most Watched News
    public List<Post> getMostWatchedNews()
    {
        return postRepository.findTop5ByOrderByViewsDesc();  // Top 5 most viewed news
    }

}
