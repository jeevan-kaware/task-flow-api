package com.jeevan.taskflowapi.service.impl;

import com.jeevan.taskflowapi.dto.request.TaskRequest;
import com.jeevan.taskflowapi.dto.request.UpdateTaskStatusRequest;
import com.jeevan.taskflowapi.dto.response.TaskResponse;
import com.jeevan.taskflowapi.entity.Project;
import com.jeevan.taskflowapi.entity.Task;
import com.jeevan.taskflowapi.entity.User;
import com.jeevan.taskflowapi.enums.Role;
import com.jeevan.taskflowapi.enums.TaskStatus;
import com.jeevan.taskflowapi.exception.BadRequestException;
import com.jeevan.taskflowapi.exception.ResourceNotFoundException;
import com.jeevan.taskflowapi.repository.ProjectRepository;
import com.jeevan.taskflowapi.repository.TaskRepository;
import com.jeevan.taskflowapi.repository.UserRepository;
import com.jeevan.taskflowapi.security.CustomUserDetails;
import com.jeevan.taskflowapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.jeevan.taskflowapi.service.EmailService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public TaskResponse createTask(TaskRequest request) {

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User user = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .project(project)
                .assignedTo(user)
                .build();

        Task savedTask = taskRepository.save(task);
        emailService.sendEmail(
                user.getEmail(),
                "New Task Assigned",
                """
                Hello %s,

                A new task has been assigned to you.

                Task : %s
                Priority : %s
                Due Date : %s

                Regards,
                Task Flow Team
                """.formatted(
                        user.getFullName(),
                        savedTask.getTitle(),
                        savedTask.getPriority(),
                        savedTask.getDueDate()
                )
        );

        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getAllTasks() {

        User currentUser = getCurrentUser();

        if(currentUser.getRole()== Role.ROLE_ADMIN){

            return taskRepository.findAll()
                    .stream()
                    .map(this::mapToResponse)
                    .toList();

        }

        return taskRepository.findByAssignedTo(currentUser)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public TaskResponse getTaskById(Long id) {

        User currentUser = getCurrentUser();

        Task task = taskRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        if(currentUser.getRole()!=Role.ROLE_ADMIN &&
                !task.getAssignedTo().getId().equals(currentUser.getId())){

            throw new RuntimeException("Access Denied");

        }

        return mapToResponse(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest request) {

        User currentUser = getCurrentUser();



        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        if(currentUser.getRole()!=Role.ROLE_ADMIN &&
                !task.getAssignedTo().getId().equals(currentUser.getId())){

            throw new RuntimeException("Access Denied");

        }

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User user = userRepository.findById(request.getAssignedUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setProject(project);
        task.setAssignedTo(user);

        return mapToResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found");
        }

        taskRepository.deleteById(id);
    }
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }
    private TaskResponse mapToResponse(Task task) {

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .assignedTo(task.getAssignedTo().getFullName())
                .projectName(task.getProject().getProjectName())
                .build();
    }
    @Override
    public List<TaskResponse> searchTasks(String keyword) {

        User currentUser = getCurrentUser();

        if(currentUser.getRole()==Role.ROLE_ADMIN){

            return taskRepository
                    .findByTitleContainingIgnoreCase(keyword)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();

        }

        return taskRepository
                .findByAssignedToAndTitleContainingIgnoreCase(
                        currentUser,
                        keyword
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public TaskResponse updateTaskStatus(
            Long id,
            UpdateTaskStatusRequest request
    ) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));
        User currentUser = getCurrentUser();

        if(currentUser.getRole()!=Role.ROLE_ADMIN &&
                !task.getAssignedTo().getId().equals(currentUser.getId())){

            throw new BadRequestException("Access denied");
        }
        task.setStatus(request.getStatus()==null
                ? TaskStatus.TODO
                : request.getStatus());

        Task updatedTask = taskRepository.save(task);

        return mapToResponse(updatedTask);
    }
}
