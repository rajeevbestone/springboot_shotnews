package com.app.news.ShotNews.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "menu_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuCategory extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "menuName")
    @NotBlank
    @Size(min = 4, message = "title must contain at least 2 characters")
    private String title;
    private String slug;

}