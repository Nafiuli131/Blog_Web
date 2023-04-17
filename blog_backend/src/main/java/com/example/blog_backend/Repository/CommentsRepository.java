package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    Comments findByBlogId(Long id);
}
