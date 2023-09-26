package com.ltp.gradesubmission.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "students")
public class Student extends RepresentationModel<Student>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Column(name = "name",nullable = false)
    private String name;
    @NonNull
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @JsonIgnore //to omit infinite loop between Grade and Student while JSON serialisation
    @OneToMany(mappedBy = "student",
                cascade = CascadeType.ALL)
    private List<Grade> grades;
}
