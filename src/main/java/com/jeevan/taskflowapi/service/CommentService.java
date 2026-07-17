package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.request.CommentRequest;
import com.jeevan.taskflowapi.dto.response.CommentResponse;
import org.springframework.data.domain.Page;


import java.util.List;

public interface CommentService {

    CommentResponse addComment(CommentRequest request);

    List<CommentResponse> getAllComments();

    void deleteComment(Long id);

    Page<CommentResponse> getComments(int page, int size);
}