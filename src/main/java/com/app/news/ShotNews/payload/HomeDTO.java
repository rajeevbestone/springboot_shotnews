package com.app.news.ShotNews.payload;

import com.app.news.ShotNews.entities.HomeSlider;
import com.app.news.ShotNews.entities.HotNews;
import com.app.news.ShotNews.entities.LiveNews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeDTO
{


  List<HomeSlider> homeSliders ;
  List<HotNews> hotNews ;
  List<LiveNews> liveNews ;




}
