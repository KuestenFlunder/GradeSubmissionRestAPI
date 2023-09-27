package com.ltp.gradesubmission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "code",unique = true)
    private String code;

    @NonNull
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL)
    private List<Grade> grades;

}
