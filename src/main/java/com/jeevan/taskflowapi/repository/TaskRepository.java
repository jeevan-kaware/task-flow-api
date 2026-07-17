package com.jeevan.taskflowapi.repository;

import com.jeevan.taskflowapi.entity.Task;
import com.jeevan.taskflowapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContainingIgnoreCase(String keyword);

    List<Task> findByAssignedTo(User user);

    List<Task> findByAssignedToAndTitleContainingIgnoreCase(
            User user,
            String keyword
    );
}