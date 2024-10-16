package com.app.news.ShotNews.controller;

import com.app.news.ShotNews.entities.Category;
import com.app.news.ShotNews.entities.Post;
import com.app.news.ShotNews.entities.Subcategory;
import com.app.news.ShotNews.exceptions.APIException;
import com.app.news.ShotNews.exceptions.ResourceNotFoundException;
import com.app.news.ShotNews.repositories.CategoryRepository;
import com.app.news.ShotNews.repositories.SubcategoryRepository;
import com.app.news.ShotNews.response.PostResponse;
import com.app.news.ShotNews.response.ResponseApi;
import com.app.news.ShotNews.services.impl.PostServiceImpl;
import com.app.news.ShotNews.services.serviceInter.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    FileService fileService;


    //    @PostMapping
//    public ResponseEntity<Post> createPost(@RequestBody Post post)
//    {
//        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
//    }
@Value("${project.image}")
private String path;

    @PostMapping()
    public ResponseEntity<Post> createPost(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("views") int views,
            @RequestParam("isHot") boolean isHot,
            @RequestParam("isSlider") boolean isSlider,
            @RequestParam("isLive") boolean isLive,
            @RequestParam("category_id") Long categoryId,
            @RequestParam("subcategory_id") Long subcategoryId,
            @RequestParam("content") String content,
            @RequestParam("urlType") String urlType,
            @RequestParam("imagePath") MultipartFile imagePath) throws IOException
    {


        System.out.println("lkshfkdfsjkhgjksdf" +categoryId);

        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, imagePath);
        } catch (IOException e)
        {
            throw new APIException("Please upload post image!");
        }

        // Create new Post object
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setViews(views);
        post.setIsHot(isHot);
        post.setIsSlider(isSlider);
        post.setIsLive(isLive);
        post.setImagePath(fileName);
        post.setContent(content);
        post.setUrlType(urlType);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", categoryId));
        post.setCategory(category);
        if (subcategoryId != null)
        {
            Subcategory subcategory = subcategoryRepository.findById(subcategoryId).orElseThrow(() -> new ResourceNotFoundException("Product", "productId", subcategoryId));
            post.setSubcategory(subcategory);
        }
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }


    @GetMapping("/{slug}")
    public ResponseEntity<ResponseApi> getPost(@PathVariable String slug)
    {

        ResponseApi responseApi =new ResponseApi();
        responseApi.setStatus(true);
        responseApi.setMessage("Found Successfully");
        responseApi.setData(postService.getPostBySlug(slug));


        return new ResponseEntity<>(responseApi, HttpStatus.OK);
    }

    @GetMapping("/most-viewed")
    public ResponseEntity<List<Post>> getMostViewedPosts()
    {
        return new ResponseEntity<>(postService.getMostViewedPosts(), HttpStatus.OK);
    }


    @GetMapping("/category/{categorySlug}")
    public List<Post> getPostsByCategorySlug( @PathVariable String categorySlug,
                                              @RequestParam(value = "subcategorySlug", required = false) String subcategorySlug)
    {
        return postService.getPostsByCategorySlug(categorySlug,subcategorySlug);
    }
}
