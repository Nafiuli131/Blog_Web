package com.example.blog_backend.Dto;

import com.example.blog_backend.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponseDto {
    private Long commentsId;
    private Long blogId;
    private String body;
    private Long userId;
    private String userName;
}
