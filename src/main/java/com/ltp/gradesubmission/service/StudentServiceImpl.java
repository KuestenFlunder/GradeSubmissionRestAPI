package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.StudentRepository;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {


    StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        return studentRepository
                .findById(id)
                .orElse(new Student());
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {    
       studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return  (List<Student>) studentRepository.findAll();
    }



}