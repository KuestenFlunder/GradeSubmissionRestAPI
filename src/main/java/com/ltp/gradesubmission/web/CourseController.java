package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    CourseService courseService;
    private final CourseRepository courseRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourse(id);
        course.add(createSelfLink(course));
        course.add(createDeleteLink(course));
        return new ResponseEntity<>(course,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        if (course != null) {
            savedCourse.add(createSelfLink(course));
            savedCourse.add(createDeleteLink(course));
        }
        return new ResponseEntity<>(savedCourse,HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getCourses();
        courses.stream()
                .peek(course -> course.add(createSelfLink(course)))
                .forEach(course -> course.add(createDeleteLink(course)));
        return new ResponseEntity<>(courses,HttpStatus.OK);
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> enrollStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        return new ResponseEntity<>(courseService.addStudentToCourse(studentId, courseId), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getEnrolledStudents(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getEnrolledStudents(id), HttpStatus.OK);
    }

    // ? Make a Class with a Generic Method to create Links from [String methodName,
    // String linkName, class T entity, class V Controller.class]
    private Link createDeleteLink(Course course) {
        Link deleteLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .deleteCourse(course.getId()))
                .withRel("delete");
        return deleteLink;
    }

    private Link createSelfLink(Course course) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .getCourse(course.getId()))
                .withRel("self");
        return selfLink;
    }


}