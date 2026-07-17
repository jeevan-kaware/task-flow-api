package com.jeevan.taskflowapi.controller;


import com.jeevan.taskflowapi.dto.request.ProjectRequest;
import com.jeevan.taskflowapi.dto.response.ProjectResponse;
import com.jeevan.taskflowapi.service.ProjectService;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {



    private final ProjectService projectService;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request
    ){

        return ResponseEntity.ok(
                projectService.createProject(request)
        );

    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(){

        return ResponseEntity.ok(
                projectService.getAllProjects()
        );

    }




    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                projectService.getProjectById(id)
        );

    }





    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request
    ){

        return ResponseEntity.ok(
                projectService.updateProject(id,request)
        );

    }




    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Long id
    ){

        projectService.deleteProject(id);

        return ResponseEntity.ok(
                "Project deleted successfully"
        );

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponse>> getProjects(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "projectName")
            String sortBy

    ) {

        return ResponseEntity.ok(

                projectService.getProjects(
                        page,
                        size,
                        sortBy
                )

        );

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponse>> searchProjects(

            @RequestParam String keyword

    ) {

        return ResponseEntity.ok(

                projectService.searchProjects(keyword)

        );

    }

}