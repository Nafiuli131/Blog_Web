package com.example.blog_backend.Controller;

import com.example.blog_backend.Dto.AllBlogsResponse;
import com.example.blog_backend.Dto.BlogRequestDto;
import com.example.blog_backend.Dto.BlogUpdateDto;
import com.example.blog_backend.Entity.Blog;
import com.example.blog_backend.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping()
    public ResponseEntity<Blog> createBlog(@RequestBody BlogRequestDto blogRequestDto){
        return blogService.createBlog(blogRequestDto);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateBlog(@RequestBody BlogUpdateDto blogUpdateDto){
        return blogService.updateBlog(blogUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id){
        return blogService.deleteBlog(id);
    }

    @GetMapping("/allBlogs")
    public ResponseEntity<List<AllBlogsResponse>> getAllBlogs(){
        return blogService.getlAllBlogs();
    }

    @GetMapping("/blogsByUser/{id}")
    public ResponseEntity<List<AllBlogsResponse>> getUserBlog(@PathVariable Long id){
        return blogService.getUserBlog(id);
    }
}