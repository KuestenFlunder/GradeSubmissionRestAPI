package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/grades")
public class GradeController {

    GradeService gradeService;

    @GetMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long studentId, @PathVariable Long courseId) {
        Grade grade = gradeService.getGrade(studentId, courseId);
        //TODO addapt with error handling
        if (grade != null) {
            grade.add(createSelfLink(studentId, courseId));
            grade.add(createDeleteLink(studentId, courseId));
        }
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @GetMapping(value = "students/{studentId}")
    public ResponseEntity<List<Grade>> getStudentsGrades(@PathVariable Long studentId) {

        return new ResponseEntity<List<Grade>>(HttpStatus.OK);
    }

    @GetMapping(value = "/courses/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable Long courseId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(gradeService.getAllGrades(),HttpStatus.OK);
    }

    @DeleteMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable Long studentId, @PathVariable Long courseId) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> saveGrade(@PathVariable Long studentId, @PathVariable Long courseId, @RequestBody Grade grade) {
        grade.add(createSelfLink(studentId, courseId));
        grade.add(createDeleteLink(studentId, courseId));
        //TODO correct implementation
        gradeService.saveGrade(grade, studentId, courseId);
        return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(@RequestBody Grade grade, @PathVariable Long studentId, @PathVariable Long courseId) {
        return new ResponseEntity<>(gradeService.updateGrade(grade.getScore(), studentId, courseId), HttpStatus.OK);}

    private Link createDeleteLink(Long studentId, Long courseId) {
        Link deleteLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(GradeController.class)
                                .deleteGrade(studentId, courseId))
                .withRel("delete");
        return deleteLink;
    }


    private Link createSelfLink(Long studentId, Long courseId) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(GradeController.class)
                                .getGrade(studentId, courseId))
                .withRel("self");
        return selfLink;
    }

}
