package com.example.fashionista.repository;

import com.example.fashionista.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment>findByPostId(Long id);
}
