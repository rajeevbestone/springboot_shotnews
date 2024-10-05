package com.app.news.ShotNews.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Trending_Topics")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrendingTopic extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "trending")
    @NotBlank
    @Size(min = 4, message = "title must contain at least 2 characters")
    private String title;
    private String slug;

}
