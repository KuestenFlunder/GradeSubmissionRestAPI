package com.ltp.gradesubmission.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.service.StudentService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        
        Link selfLink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(StudentController.class)
                .getStudent(student.getId()))
                .withRel("self");
        Link deleteLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                    .methodOn(StudentController.class)
                    .deleteStudent(student.getId()))
                    .withRel("delete");
        student.add(selfLink);
        student.add(deleteLink);

        return new ResponseEntity<Student>(student,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id){
        //TODO Return the deleted student for cross-checking
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
