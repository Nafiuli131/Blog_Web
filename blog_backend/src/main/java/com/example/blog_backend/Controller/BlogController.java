package com.example.blog_backend.Controller;

import com.example.blog_backend.Dto.BlogRequestDto;
import com.example.blog_backend.Dto.BlogUpdateDto;
import com.example.blog_backend.Entity.Blog;
import com.example.blog_backend.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
