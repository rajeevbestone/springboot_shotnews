package com.app.news.ShotNews.services.impl;


import com.app.news.ShotNews.config.AppConstant;
import com.app.news.ShotNews.entities.AppSettings;
import com.app.news.ShotNews.entities.MenuCategory;
import com.app.news.ShotNews.entities.TrendingTopic;
import com.app.news.ShotNews.payload.MenuDTO;
import com.app.news.ShotNews.payload.SettingsDTO;
import com.app.news.ShotNews.repositories.CreateAboutRepo;
import com.app.news.ShotNews.repositories.MenuRepo;
import com.app.news.ShotNews.repositories.TrendingTopicRepo;
import com.app.news.ShotNews.services.serviceInter.FileService;
import com.app.news.ShotNews.services.serviceInter.MenuService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl  implements MenuService {

    @Autowired
    MenuRepo menuRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TrendingTopicRepo trendingTopicRepo;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    CreateAboutRepo createAboutRepo;

    @Override
    public String createMenu(List<MenuDTO> menuDTO)
    {

        List<MenuCategory> cartDTOs = menuDTO.stream().map(cart ->
        {
            MenuCategory cartDTO = modelMapper.map(cart, MenuCategory.class);
            cartDTO.setSlug(cart.getTitle());
           return cartDTO;
          }).collect(Collectors.toList());

         menuRepo.saveAll(cartDTOs);
        return "";
    }

    @Override
    public String createTrending(List<MenuDTO> menuDTO)
    {
        List<TrendingTopic> cartDTOs = menuDTO.stream().map(cart ->
        {
            TrendingTopic cartDTO = modelMapper.map(cart, TrendingTopic.class);
            cartDTO.setSlug(cart.getTitle());
            return cartDTO;
        }).collect(Collectors.toList());
        trendingTopicRepo.saveAll(cartDTOs);
        return "";
    }


    @Override
    public List<MenuDTO> getALLMenu()
     {
         List<MenuCategory> menuCategories=menuRepo.findAll();
        return  menuCategories.stream().map(menuCategory -> modelMapper.map(menuCategory,MenuDTO.class)).collect(Collectors.toList());
     }



    @Override
    public Boolean createAbout(String about, MultipartFile img)
    {

        try {
            AppSettings appSettings=new AppSettings();
            String fileName = fileService.uploadImage(path, img);
            appSettings.setAbout(about);
            appSettings.setImage(fileName);
            createAboutRepo.save(appSettings);

           } catch (IOException e)
          {
            throw new RuntimeException(e);
          }

        return true;
    }

    @Override
    public SettingsDTO getALLSetting()
    {

        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        SettingsDTO settingsDTO=new SettingsDTO();


        List<MenuCategory> menuCategories=menuRepo.findAll();
        List<TrendingTopic> trendingTopics=trendingTopicRepo.findAll();

        List<AppSettings> list=  createAboutRepo.findAll();

        AppSettings firstSetting = list.get(0); // Get the first element


        String image = firstSetting.getImage();
        String description = firstSetting.getAbout();

        settingsDTO.setDescription(description);
        settingsDTO.setImage(baseUrl+ AppConstant.imageUrl+image);

         settingsDTO.setMenu(menuCategories.stream().map(menuCategory -> modelMapper.map(menuCategory,MenuDTO.class)).collect(Collectors.toList()));

        settingsDTO.setTrendingTopics(trendingTopics.stream().map(trendingTopic -> modelMapper.map(trendingTopic,MenuDTO.class)).collect(Collectors.toList()));


        return settingsDTO;
    }


}
