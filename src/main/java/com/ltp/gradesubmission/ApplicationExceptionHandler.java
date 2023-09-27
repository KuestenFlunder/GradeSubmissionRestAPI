package com.ltp.gradesubmission;

import com.ltp.gradesubmission.exceptions.CourseNotFoundException;
import com.ltp.gradesubmission.exceptions.ErrorResponse;
import com.ltp.gradesubmission.exceptions.GradeNotFoundException;
import com.ltp.gradesubmission.exceptions.StudentNotFoundException;
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

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CourseNotFoundException.class, GradeNotFoundException.class, StudentNotFoundException.class})
    public ResponseEntity<Object> handeleRecourceNotFoundExceptions(RuntimeException ex){
        return  new ResponseEntity<>(
                new ErrorResponse(Arrays.asList(ex.getMessage())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResltDataAccessException(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse(Arrays.asList("Cannot delete non-existing resource")),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(RuntimeException ex){
        return new ResponseEntity<>(
                new ErrorResponse(Arrays.asList("Data Integrity Violation: we cannot process your request")),
                HttpStatus.BAD_REQUEST);
    }

}
