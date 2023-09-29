package com.ltp.gradesubmission.exceptions;

public class StudentNotEnrolledException extends RuntimeException{

    public StudentNotEnrolledException(Long studentId, Long courseId){

                super(String.format("The student %d is not enrolled in course %d",studentId,courseId));

    };
}
