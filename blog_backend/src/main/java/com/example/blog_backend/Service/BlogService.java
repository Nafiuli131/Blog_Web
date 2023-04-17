package com.example.blog_backend.Service;

import com.example.blog_backend.CustomException.CustomException;
import com.example.blog_backend.Dto.BlogRequestDto;
import com.example.blog_backend.Dto.BlogUpdateDto;
import com.example.blog_backend.Entity.Blog;
import com.example.blog_backend.Entity.Comments;
import com.example.blog_backend.Entity.User;
import com.example.blog_backend.Repository.BlogRepository;
import com.example.blog_backend.Repository.CommentsRepository;
import com.example.blog_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    CommentsRepository commentsRepository;

    public ResponseEntity<Blog> createBlog(BlogRequestDto blogRequestDto) {
        Blog blog = new Blog();
        blog.setBody(blogRequestDto.getBody());
        User user = userRepository.findById(blogRequestDto.getUserId());
        if(Objects.isNull(user)){
            throw new CustomException("no user found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
        return ResponseEntity.ok(blog);
    }

    public ResponseEntity<String> updateBlog(BlogUpdateDto blogUpdateDto) {
        Optional<Blog> blogOptional = blogRepository.findById(blogUpdateDto.getId());
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.setBody(blogUpdateDto.getBody());
            blogRepository.save(blog);
            return ResponseEntity.ok("Updated Successfully");
        } else {
            return ResponseEntity.ok("Updated failed");
        }
    }

    public ResponseEntity<String> deleteBlog(Long id) {
        return blogRepository.findById(id)
                .map(blog -> {
                    Comments comments = commentsRepository.findByBlogId(id);
                    if(Objects.nonNull(comments)){
                        commentsRepository.deleteById(comments.getId());
                    }
                    blogRepository.delete(blog);
                    return ResponseEntity.ok("Deleted successfully");
                })
                .orElse(ResponseEntity.ok("Deleted failed"));
    }
}
