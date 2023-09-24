package com.ltp.gradesubmission.entity;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;




public class Student extends RepresentationModel<Student>{

    private Long id;
    private String name;
    private LocalDate birthDate;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
