package com.jeevan.taskflowapi.repository;

import com.jeevan.taskflowapi.entity.Comment;
import com.jeevan.taskflowapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}