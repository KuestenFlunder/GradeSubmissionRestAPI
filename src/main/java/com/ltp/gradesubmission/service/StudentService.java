package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Set;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;

public interface StudentService {
    Student getStudent(Long id);

    Student addCourseToStudent(Long studentId, Long courseId);

    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();

    Set<Course> getEnrolledCourses(Long studentId);
}