package com.jeevan.taskflowapi.controller;

import com.jeevan.taskflowapi.dto.request.TaskRequest;
import com.jeevan.taskflowapi.dto.request.UpdateTaskStatusRequest;
import com.jeevan.taskflowapi.dto.response.TaskResponse;
import com.jeevan.taskflowapi.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign")
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.ok(taskService.createTask(request));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {

        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {

        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok("Task deleted successfully");
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(

            @RequestParam String keyword

    ) {

        return ResponseEntity.ok(

                taskService.searchTasks(keyword)

        );

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(

            @PathVariable Long id,

            @Valid
            @RequestBody
            UpdateTaskStatusRequest request

    ) {

        return ResponseEntity.ok(

                taskService.updateTaskStatus(id, request)

        );

    }
}