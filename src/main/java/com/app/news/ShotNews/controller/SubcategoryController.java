package com.app.news.ShotNews.controller;
import com.app.news.ShotNews.config.AppConstant;
import com.app.news.ShotNews.config.SlugUtils;
import com.app.news.ShotNews.entities.Category;
import com.app.news.ShotNews.entities.Subcategory;
import com.app.news.ShotNews.exceptions.APIException;
import com.app.news.ShotNews.repositories.CategoryRepository;
import com.app.news.ShotNews.repositories.SubcategoryRepository;
import com.app.news.ShotNews.response.ResponseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @PostMapping
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Subcategory subcategory)
    {
        subcategory.setSlug(SlugUtils.toSlug(subcategory.getName())); // Create slug
        if (subcategoryRepository.existsByName(subcategory.getName())) {
            throw new APIException("Name '" + subcategory.getName() + "' already exists. Please use a different name.");
        }
        return new ResponseEntity<>(subcategoryRepository.save(subcategory), HttpStatus.CREATED);
    }

    @GetMapping("/getSubCategory")
    public ResponseEntity<ResponseApi> getALLMenu()
    {
        return  new ResponseEntity<>(ResponseApi
                .builder()
                .status(true)
                .message(AppConstant.response)
                .data( subcategoryRepository.findAll())
                .build(), HttpStatus.CREATED);
    }
}
