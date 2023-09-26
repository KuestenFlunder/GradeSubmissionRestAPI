package com.ltp.gradesubmission.entity;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grades")

public class Grade extends RepresentationModel<Grade> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "score",nullable = false)
    private String score;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name="course_id",referencedColumnName = "id")
    private Course course;
}
