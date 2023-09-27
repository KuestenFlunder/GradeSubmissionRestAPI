package com.ltp.gradesubmission.web;

import java.util.List;

import lombok.AllArgsConstructor;
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

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {


    StudentService studentService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        //! getStudent returns null if student is not found in the db, fix this with error handling implementation
        Student student = studentService.getStudent(id);
        student.add(createDeleteLink(student));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        studentService.getStudents().forEach(student -> student.add(createSelfLink(student)));
        studentService.getStudents().forEach(student -> student.add(createDeleteLink(student)));
        return new ResponseEntity<>(studentService.getStudents(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        student.add(createSelfLink(savedStudent));
        student.add(createDeleteLink(savedStudent));
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    // ? Make a Class with a Generic Method to create Links from [String methodName,
    // String linkName, class T entity, class V Controller.class]
    private Link createDeleteLink( Student student) {
        Link deleteLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(StudentController.class)
                        .deleteStudent(student.getId()))
                .withRel("delete");
        return deleteLink;
    }

    private Link createSelfLink(Student student) {
        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder
                        .methodOn(StudentController.class)
                        .getStudent(student.getId()))
                .withRel("self");
        return selfLink;
    }

}
