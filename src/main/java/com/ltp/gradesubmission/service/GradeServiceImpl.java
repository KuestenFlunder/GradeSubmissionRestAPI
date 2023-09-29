package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exceptions.CourseNotFoundException;
import com.ltp.gradesubmission.exceptions.GradeNotFoundException;
import com.ltp.gradesubmission.exceptions.StudentNotEnrolledException;
import com.ltp.gradesubmission.exceptions.StudentNotFoundException;
import com.ltp.gradesubmission.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor // alternative to Autowired and preferred if there are many dependencies
@Service
public class GradeServiceImpl implements GradeService {


    GradeRepository gradeRepository;

    StudentService studentService;
    CourseService courseService;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        return gradeRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new GradeNotFoundException(studentId, courseId));
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        // Extract optional handling to the Service it belongs to increase business logic incapsulation
        Student student = studentService.getStudent(studentId);
        Course course = courseService.getCourse(courseId);

          if(courseService.getEnrolledStudents(courseId).contains(student)){
            grade.setStudent(student);
            grade.setCourse(course);
            return gradeRepository.save(grade);
        } else {
            throw new StudentNotEnrolledException(studentId, courseId);
        }
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        Grade grade = gradeRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new GradeNotFoundException(studentId, courseId));
        grade.setScore(score);
        return gradeRepository.save(grade);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository
                .findByStudentId(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository
                .findByCourseId(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository
                .findAll();
    }

}
