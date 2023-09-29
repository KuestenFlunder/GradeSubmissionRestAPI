package com.ltp.gradesubmission;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseNotFoundException.class, GradeNotFoundException.class, StudentNotFoundException.class})
    public ResponseEntity<Object> handeleRecourceNotFoundExceptions(RuntimeException ex){
        return  new ResponseEntity<>(
                new ErrorResponse(Collections.singletonList(ex.getMessage())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResltDataAccessException(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse(List.of("Cannot delete non-existing resource")),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse(List.of("Data Integrity Violation: we cannot process your request")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StudentNotEnrolledException.class)
    public ResponseEntity<Object> handleStudentNotEnrolledException(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse(Collections.singletonList(ex.getMessage())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<Object> handleUserNotFoundException(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse(Collections.singletonList(ex.getMessage())),
                HttpStatus.NOT_FOUND);
    }

}
