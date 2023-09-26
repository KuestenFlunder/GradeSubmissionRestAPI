package com.ltp.gradesubmission.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")

public class Course extends RepresentationModel<Course> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "subject",nullable = false)
    private String subject;
    @Column(name = "code",nullable = false)
    private String code;
    @Column(name = "description",nullable = false)
    private String description;
}
