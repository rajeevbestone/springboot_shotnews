package com.app.news.ShotNews.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDTO
{

    private List<MenuDTO> menu;
    private List<MenuDTO> trendingTopics;
    private String image;
    private  String description;



}
