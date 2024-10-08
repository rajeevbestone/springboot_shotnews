package com.app.news.ShotNews.services.impl;

import com.app.news.ShotNews.entities.HomeSlider;
import com.app.news.ShotNews.entities.HotNews;
import com.app.news.ShotNews.entities.LiveNews;
import com.app.news.ShotNews.payload.HomeDTO;
import com.app.news.ShotNews.repositories.HomeSliderRepo;
import com.app.news.ShotNews.repositories.HotNewsRepo;
import com.app.news.ShotNews.repositories.LiveNewsRepo;
import com.app.news.ShotNews.services.serviceInter.FileService;
import com.app.news.ShotNews.services.serviceInter.HomeNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class HomeNewsServiceImplements implements HomeNewsService
{

    @Autowired
    HotNewsRepo hotNewsRepo;

    @Autowired
    FileService fileService;


    @Autowired
    HomeSliderRepo homeSliderRepo;

    @Autowired
    LiveNewsRepo liveNewsRepo;

    @Value("${project.image}")
    private String path;


    @Override
    public Boolean createdHotNews(String title, String description,  MultipartFile img)
    {

        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, img);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        HotNews hotNews=HotNews.builder().title(title).description(description).slug("hotnews").image(fileName).build();

     HotNews hotNews1=    hotNewsRepo.save(hotNews);
        if (hotNews1!=null)
        {
            return true;
        }
        else {
            return false;
        }


    }

    @Override
    public Boolean createHomeSlider(String title, String description,  MultipartFile img) {
        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, img);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        HomeSlider homeSlider=HomeSlider.builder().title(title).description(description).slug("home_slider").image(fileName).build();

        HomeSlider homeSlider1=    homeSliderRepo.save(homeSlider);


        if (homeSlider1!=null)
        {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Boolean createLiveNews(String title, String description, String urlType, MultipartFile url) {


        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, url);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        LiveNews homeSlider=LiveNews.builder().title(title).description(description).slug("live_news").url(fileName).urlType(urlType).build();

        LiveNews homeSlider1=    liveNewsRepo.save(homeSlider);


        if (homeSlider1!=null)
        {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public HomeDTO getHomePageData()
     {

         HomeDTO homeDTO=new HomeDTO();

         List<HotNews> hotNewsList=hotNewsRepo.findAll();
         List<HomeSlider> homeSliders=homeSliderRepo.findAll();
         List<LiveNews> liveNewsList=liveNewsRepo.findAll();
         homeDTO.setHomeSliders(homeSliders);
         homeDTO.setLiveNews(liveNewsList);
         homeDTO.setHotNews(hotNewsList);


        return homeDTO;
     }


}
