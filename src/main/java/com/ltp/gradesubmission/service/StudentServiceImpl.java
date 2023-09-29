package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exceptions.StudentNotFoundException;
import com.ltp.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {


    StudentRepository studentRepository;
    CourseService courseService;

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

    }
    @Override
    public Student addCourseToStudent(Long studentId, Long courseId) {
        Course course = courseService.getCourse(courseId);
        Student student = getStudent(studentId);
        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Set<Course> getEnrolledCourses(Long id) {
        Student student = getStudent(id);
        return student.getCourses();
    }


}