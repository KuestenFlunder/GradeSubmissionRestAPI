package com.ltp.gradesubmission.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Grade;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<Grade> saveGrade(@PathVariable Long studentId, @PathVariable Long courseId,@RequestBody Grade grade) {
        grade.add(getSelfLink(studentId, courseId));
        grade.add(getDeleteLink(studentId, courseId));
        return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }

   
    private Link getDeleteLink(Long studentId, Long courseId) {
        Link deleteLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(GradeController.class)
                        .deleteGrade(studentId, courseId))
                .withRel("delete");
        return deleteLink;
    }

    private Link getSelfLink(Long studentId, Long courseId) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(GradeController.class)
                        .getGrade(studentId, courseId))
                .withRel("self");
        return selfLink;
    }

}
