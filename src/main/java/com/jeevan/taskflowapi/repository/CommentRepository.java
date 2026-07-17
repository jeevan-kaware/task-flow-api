package com.jeevan.taskflowapi.repository;

import com.jeevan.taskflowapi.entity.Comment;
import com.jeevan.taskflowapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
    Page<Comment> findByUser(User user, Pageable pageable);
}