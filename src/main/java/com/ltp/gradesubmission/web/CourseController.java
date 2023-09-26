package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {
    CourseService courseService;

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
//        if (course != null) {
//            course.add(getSelfLink(course));
//            course.add(getDeleteLink(course));
//        }
        return new ResponseEntity<>(courseService.saveCourse(course),HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ? Make a Class with a Generic Method to create Links from [String methodName,
    // String linkName, class T entity, class V Controller.class]
    private Link getDeleteLink(Course course) {
        Link deleteLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .deleteCourse(course.getId()))
                .withRel("delete");
        return deleteLink;
    }

    private Link getSelfLink(Course course) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(CourseController.class)
                                .getCourse(course.getId()))
                .withRel("self");
        return selfLink;
    }


}