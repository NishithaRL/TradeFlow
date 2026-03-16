package com.tradeflow.tradeservice.exception;

import com.tradeflow.tradeservice.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ApiResponse<String> response = new ApiResponse<>(
                false,
                message,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(org.springframework.security.access.AccessDeniedException ex) {

        ApiResponse<String> response = new ApiResponse<>(
                false,
                "You do not have permission to perform this action",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    // Custom service exceptions
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFound(ResponseStatusException ex) {

        ApiResponse<String> response = new ApiResponse<>(
                false,
                ex.getReason(),
                null
        );

        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    // Any unexpected error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex) {

        ApiResponse<String> response = new ApiResponse<>(
                false,
                "Something went wrong",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}