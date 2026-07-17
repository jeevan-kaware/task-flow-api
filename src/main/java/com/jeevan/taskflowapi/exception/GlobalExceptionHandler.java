package com.jeevan.taskflowapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFound(
            ResourceNotFoundException ex
    ){

        ErrorResponse response =
                ErrorResponse.builder()

                        .success(false)

                        .message(ex.getMessage())

                        .timestamp(LocalDateTime.now())

                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );

    }




    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> duplicate(
            DuplicateResourceException ex
    ){

        ErrorResponse response =
                ErrorResponse.builder()

                        .success(false)

                        .message(ex.getMessage())

                        .timestamp(LocalDateTime.now())

                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
        );

    }




    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequest(
            BadRequestException ex
    ){

        ErrorResponse response =
                ErrorResponse.builder()

                        .success(false)

                        .message(ex.getMessage())

                        .timestamp(LocalDateTime.now())

                        .build();


        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }





    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationException(
            MethodArgumentNotValidException ex
    ){


        Map<String,String> errors = new HashMap<>();


        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );


        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );

    }





    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalException(
            Exception ex
    ){

        ErrorResponse response =
                ErrorResponse.builder()

                        .success(false)

                        .message(ex.getMessage())

                        .timestamp(LocalDateTime.now())

                        .build();



        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }


}