package com.ltp.gradesubmission.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @GetMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "students/{studentId}")
    public ResponseEntity<List<Grade>> getStudentsGrades(@PathVariable Long studentId) {

        return new ResponseEntity<List<Grade>>(HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Grade>> getGrades(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long studentId, @PathVariable Long courseId) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade) {
       //TODO Add Hateoas to Method
        return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }

    // // ? Make a Class with a Generic Method to create Links from [String methodName,
    // // String linkName, class T entity, class V Controller.class]
    // private Link getDeleteLink(Grade grade) {
    //     Link deleteLink = WebMvcLinkBuilder.linkTo(
    //             WebMvcLinkBuilder
    //                     .methodOn(StudentController.class)
    //                     .deleteStudent(grade.getId()))
    //             .withRel("delete");
    //     return deleteLink;
    // }

    // private Link getSelfLink(Grade grade) {
    //     Link selfLink = WebMvcLinkBuilder.linkTo(
    //             WebMvcLinkBuilder
    //                     .methodOn(StudentController.class)
    //                     .getStudent(grade.getId()))
    //             .withRel("self");
    //     return selfLink;
    //}

}
