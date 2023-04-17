package com.example.blog_backend.Service;

import com.example.blog_backend.Dto.CommentsRequestDto;
import com.example.blog_backend.Dto.CommentsResponseDto;
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
public class CommentsService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    CommentsRepository commentsRepository;
    @Autowired
    UserRepository userRepository;
    public ResponseEntity<CommentsResponseDto> createComments(CommentsRequestDto commentsRequestDto) {
        Optional<Blog> blog = blogRepository.findById(commentsRequestDto.getBlogId());
        if(blog.isPresent()){
            Comments comments = new Comments();
            comments.setBody(commentsRequestDto.getBody());
            comments.setBlog(blog.get());
            User user = userRepository.findById(commentsRequestDto.getUserId());
            comments.setUser(user);
            commentsRepository.save(comments);
            CommentsResponseDto commentsResponseDto = new CommentsResponseDto();
            commentsResponseDto.setCommentsId(comments.getId());
            commentsResponseDto.setBody(comments.getBody());
            commentsResponseDto.setBlogId(blog.get().getId());
            commentsResponseDto.setUserId(user.getId());
            commentsResponseDto.setUserName(user.getName());
            return ResponseEntity.ok(commentsResponseDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteComments(Long id) {
        return commentsRepository.findById(id)
                .map(comment -> {
                    commentsRepository.delete(comment);
                    return ResponseEntity.ok("Deleted successfully");
                })
                .orElse(ResponseEntity.ok("Deleted failed"));
    }

}
