package com.example.blog_backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllBlogsResponse {
    private Long blogId;
    private String blogBody;
    private Long blogUserId;
    private List<CommentsResponseDto> commentsResponseDtoList;
}
