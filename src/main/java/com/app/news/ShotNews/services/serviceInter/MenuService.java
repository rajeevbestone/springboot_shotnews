package com.app.news.ShotNews.services.serviceInter;

import com.app.news.ShotNews.payload.MenuDTO;
import com.app.news.ShotNews.payload.SettingsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {

    String createMenu(List<MenuDTO> menuDTO);
    String createTrending(List<MenuDTO> menuDTO);

    List<MenuDTO> getALLMenu( );
     SettingsDTO getALLSetting();

    Boolean createAbout(String about, MultipartFile img);
}
