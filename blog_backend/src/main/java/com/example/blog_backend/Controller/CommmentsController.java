package com.example.blog_backend.Controller;

import com.example.blog_backend.Dto.CommentsRequestDto;
import com.example.blog_backend.Dto.CommentsResponseDto;
import com.example.blog_backend.Entity.Comments;
import com.example.blog_backend.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommmentsController {
    @Autowired
    CommentsService commentsService;
    @PostMapping()
    public ResponseEntity<CommentsResponseDto> createComments(@RequestBody CommentsRequestDto commentsRequestDto){
        return commentsService.createComments(commentsRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComments(@PathVariable Long id){
        return commentsService.deleteComments(id);
    }
}
