package com.ltp.gradesubmission.entity;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "courses")

public class Course extends RepresentationModel<Course> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "subject")
    private String subject;

    @NonNull
    @Column(name = "code")
    private String code;

    @NonNull
    @Column(name = "description")
    private String description;
}
