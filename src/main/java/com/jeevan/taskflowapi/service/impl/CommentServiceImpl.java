package com.jeevan.taskflowapi.service.impl;


import com.jeevan.taskflowapi.dto.request.CommentRequest;
import com.jeevan.taskflowapi.dto.response.CommentResponse;
import com.jeevan.taskflowapi.entity.Comment;
import com.jeevan.taskflowapi.entity.Task;
import com.jeevan.taskflowapi.entity.User;
import com.jeevan.taskflowapi.enums.Role;
import com.jeevan.taskflowapi.exception.ResourceNotFoundException;
import com.jeevan.taskflowapi.repository.CommentRepository;
import com.jeevan.taskflowapi.repository.TaskRepository;
import com.jeevan.taskflowapi.repository.UserRepository;
import com.jeevan.taskflowapi.security.CustomUserDetails;
import com.jeevan.taskflowapi.service.CommentService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @Override
    public CommentResponse addComment(CommentRequest request) {


        Task task = taskRepository.findById(request.getTaskId())

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found"
                        )
                );


        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );


        Comment comment = Comment.builder()

                .message(request.getMessage())

                .task(task)

                .user(user)

                .createdAt(LocalDateTime.now())

                .build();



        return mapToResponse(
                commentRepository.save(comment)
        );

    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }



    @Override
    public List<CommentResponse> getAllComments() {

        User currentUser = getCurrentUser();

        List<Comment> comments;

        if (currentUser.getRole() == Role.ROLE_ADMIN) {

            comments = commentRepository.findByUser(currentUser);

        } else {

            comments = commentRepository.findAll()
                    .stream()
                    .filter(comment -> comment.getUser().getId().equals(currentUser.getId()))
                    .toList();

        }

        return comments.stream()
                .map(this::mapToResponse)
                .toList();
    }





    @Override
    public void deleteComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        User currentUser = getCurrentUser();

        if(currentUser.getRole() == Role.ROLE_ADMIN ||
                comment.getUser().getId().equals(currentUser.getId())){

            commentRepository.delete(comment);

        }else{

            throw new ResourceNotFoundException("You are not allowed to delete this comment");

        }

    }

    @Override
    public List<CommentResponse> getComments(int page, int size) {

        User currentUser = getCurrentUser();

        Pageable pageable = PageRequest.of(page, size);

        Page<Comment> comments;

        if (currentUser.getRole() == Role.ROLE_ADMIN) {

            comments = commentRepository.findAll(pageable);

        } else {

            comments = commentRepository.findByUser(currentUser, pageable);

        }

        return comments.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private CommentResponse mapToResponse(Comment comment){


        return CommentResponse.builder()

                .id(comment.getId())

                .message(comment.getMessage())

                .commentedBy(
                        comment.getUser().getFullName()
                )

                .createdAt(
                        comment.getCreatedAt()
                )

                .build();

    }
}