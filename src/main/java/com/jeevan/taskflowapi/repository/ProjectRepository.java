package com.jeevan.taskflowapi.repository;

import com.jeevan.taskflowapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByProjectNameContainingIgnoreCase(String keyword);

}