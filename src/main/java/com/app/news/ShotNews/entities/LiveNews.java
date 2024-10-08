package com.app.news.ShotNews.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "live_news")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 4, message = "title must contain at least 2 characters")
    private String title;

    private String slug;

    @Column(length = 100000)
    private String description;
    private String url;
    private String urlType;
}
