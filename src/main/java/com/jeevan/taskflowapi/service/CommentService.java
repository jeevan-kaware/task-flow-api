package com.jeevan.taskflowapi.service;

import com.jeevan.taskflowapi.dto.request.CommentRequest;
import com.jeevan.taskflowapi.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse addComment(CommentRequest request);

    List<CommentResponse> getCommentsByTask(Long taskId);

    void deleteComment(Long id);

}