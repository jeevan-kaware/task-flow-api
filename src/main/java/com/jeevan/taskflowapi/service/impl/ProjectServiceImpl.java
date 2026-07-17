package com.jeevan.taskflowapi.service.impl;


import com.jeevan.taskflowapi.dto.request.ProjectRequest;
import com.jeevan.taskflowapi.dto.response.ProjectResponse;
import com.jeevan.taskflowapi.entity.Project;
import com.jeevan.taskflowapi.exception.ResourceNotFoundException;
import com.jeevan.taskflowapi.repository.ProjectRepository;
import com.jeevan.taskflowapi.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {


    private final ProjectRepository projectRepository;


    @Override
    public ProjectResponse createProject(ProjectRequest request) {


        Project project = Project.builder()

                .projectName(request.getProjectName())

                .description(request.getDescription())

                .startDate(request.getStartDate())

                .endDate(request.getEndDate())

                .build();


        Project savedProject =
                projectRepository.save(project);



        return mapToResponse(savedProject);

    }



    @Override
    public List<ProjectResponse> getAllProjects() {


        return projectRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();

    }




    @Override
    public ProjectResponse getProjectById(Long id) {


        Project project =
                projectRepository.findById(id)

                        .orElseThrow(
                                () -> new ResourceNotFoundException("Project not found")
                        );


        return mapToResponse(project);

    }





    @Override
    public ProjectResponse updateProject(
            Long id,
            ProjectRequest request
    ) {


        Project project =
                projectRepository.findById(id)

                        .orElseThrow(
                                () -> new ResourceNotFoundException("Project not found")
                        );



        project.setProjectName(
                request.getProjectName()
        );


        project.setDescription(
                request.getDescription()
        );


        project.setStartDate(
                request.getStartDate()
        );


        project.setEndDate(
                request.getEndDate()
        );


        Project updated =
                projectRepository.save(project);



        return mapToResponse(updated);

    }





    @Override
    public void deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("project not found");
        }
        projectRepository.deleteById(id);

    }





    private ProjectResponse mapToResponse(Project project){


        return ProjectResponse.builder()

                .id(project.getId())

                .projectName(
                        project.getProjectName()
                )

                .description(
                        project.getDescription()
                )

                .startDate(
                        project.getStartDate()
                )

                .endDate(
                        project.getEndDate()
                )

                .build();

    }
    @Override
    public List<ProjectResponse> getProjects(
            int page,
            int size,
            String sortBy
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );

        Page<Project> projectPage =
                projectRepository.findAll(pageable);

        return projectPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<ProjectResponse> searchProjects(String keyword) {

        return projectRepository
                .findByProjectNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


}