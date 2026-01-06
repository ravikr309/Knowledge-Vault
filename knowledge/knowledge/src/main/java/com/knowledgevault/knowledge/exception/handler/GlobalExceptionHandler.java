package com.knowledgevault.knowledge.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.knowledgevault.knowledge.dto.ApiErrorResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 1. Handle "Not Found" (404)
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFound(EntityNotFoundException ex) {
    ApiErrorResponse error = new ApiErrorResponse(
        ex.getMessage(),
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  // 2. Handle "Bad Request" / Type Mismatch (400)
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiErrorResponse> handleBadRequest(IllegalArgumentException ex) {
    ApiErrorResponse error = new ApiErrorResponse(
        ex.getMessage(),
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  // 3. Handle Validation Failures (400)
  // Converts multiple field errors into one readable string
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    String details = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));

    ApiErrorResponse error = new ApiErrorResponse(
        "Validation Failed: " + details,
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  // 4. Global Fallback (500)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGeneral(Exception ex) {
    ApiErrorResponse error = new ApiErrorResponse(
        "An unexpected error occurred",
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}