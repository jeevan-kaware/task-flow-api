package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.request.ProjectRequest;
import com.jeevan.taskflowapi.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest request);

    List<ProjectResponse> getAllProjects();

    ProjectResponse getProjectById(Long id);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void deleteProject(Long id);

    List<ProjectResponse> getProjects(
            int page,
            int size,
            String sortBy
    );
    List<ProjectResponse> searchProjects(String keyword);
}