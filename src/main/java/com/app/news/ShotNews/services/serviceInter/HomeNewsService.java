package com.app.news.ShotNews.services.serviceInter;

import com.app.news.ShotNews.payload.HomeDTO;
import org.springframework.web.multipart.MultipartFile;

public interface HomeNewsService {
    Boolean createdHotNews(String title, String description, MultipartFile img);
    Boolean createHomeSlider(String title, String description, MultipartFile img);
    Boolean createLiveNews(String title, String description, String urlType, MultipartFile url);

    Boolean createGroundLevel(String title, String description, String origin,MultipartFile url);

    HomeDTO getHomePageData();
}
