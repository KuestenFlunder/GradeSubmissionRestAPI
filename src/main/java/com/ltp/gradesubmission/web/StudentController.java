package com.ltp.gradesubmission.web;

import java.util.List;

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
        //only for test purpose, 
        //! change to StudentService.getStudentById() when implemented
        Student student = new Student();
        student.add(getDeleteLink(student));
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        student.add(getSelfLink(student));
        student.add(getDeleteLink(student));

        return new ResponseEntity<Student>(student,HttpStatus.CREATED);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id){
        //TODO Return the deleted student for cross-checking
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
    

    //? Make a Class with a Generic Method to create Links from [String methodName, String linkName, class T entity, class V Controller.class]
    private Link getDeleteLink(Student student) {
        Link deleteLink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(StudentController.class)
                .deleteStudent(student.getId()))
                .withRel("delete");
        return deleteLink;
    }
    
    private Link getSelfLink(Student student) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder
                .methodOn(StudentController.class)
                .getStudent(student.getId()))
                .withRel("self");
        return selfLink;
    }

}
