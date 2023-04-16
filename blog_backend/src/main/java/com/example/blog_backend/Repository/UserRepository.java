package com.example.blog_backend.Repository;

import com.example.blog_backend.Entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Id> {
    User findByMail(String mail);

    User findById(Long userId);
}
