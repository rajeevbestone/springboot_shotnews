package com.app.news.ShotNews.payload;

import com.app.news.ShotNews.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO  {

    private Long id;
    private String title;
}
