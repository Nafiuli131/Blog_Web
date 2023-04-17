package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query("select bg from Blog bg where bg.userId = :id")
    List<Blog> findByUserId(Long id);
}
