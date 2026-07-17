package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.request.TaskRequest;
import com.jeevan.taskflowapi.dto.request.UpdateTaskStatusRequest;
import com.jeevan.taskflowapi.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    TaskResponse updateTask(Long id, TaskRequest request);

    void deleteTask(Long id);
    List<TaskResponse> searchTasks(String keyword);
    TaskResponse updateTaskStatus(
            Long id,
            UpdateTaskStatusRequest request
    );
}