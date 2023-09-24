package com.ltp.gradesubmission.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@RequestMapping("/grades")
public class GradeController {
    
    @GetMapping(value="/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> getMethodName(@PathVariable Long studentId,@PathVariable Long courseId) {
        
        return new ResponseEntity<>(HttpStatus.OK);
    }


    
    @PostMapping(value = "/students/{studentId}/courses/{courseId}")
    public ResponseEntity<Grade> saveGrade(@RequestBody Grade grade){
        return new ResponseEntity<>(grade,HttpStatus.CREATED);
    }


}
