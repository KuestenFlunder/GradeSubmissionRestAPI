package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exceptions.EntityNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
 private StudentRepository studentRepository;

    @Override
    public Course getCourse(Long id) {
        return courseRepository.
                findById(id).
                orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }


    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = getCourse(courseId);
        course.getStudents().add( studentRepository.
                findById(studentId).
                orElseThrow(()->new EntityNotFoundException(studentId,Student.class)));
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }


}
