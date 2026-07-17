package com.jeevan.taskflowapi.controller;


import com.jeevan.taskflowapi.dto.request.CommentRequest;
import com.jeevan.taskflowapi.dto.response.CommentResponse;
import com.jeevan.taskflowapi.service.CommentService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(

            @Valid @RequestBody CommentRequest request

    ){

        return ResponseEntity.ok(
                commentService.addComment(request)
        );

    }




    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments(){

        return ResponseEntity.ok(
                commentService.getAllComments()
        );

    }




    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long id
    ) {

        commentService.deleteComment(id);

        return ResponseEntity.ok("Comment deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all")
    public Page<CommentResponse> getComments(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        return commentService.getComments(page, size);
    }

}