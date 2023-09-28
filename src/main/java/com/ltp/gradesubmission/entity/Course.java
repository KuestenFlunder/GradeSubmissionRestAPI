package com.ltp.gradesubmission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

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
    @NotBlank(message = "Subject can not be blank")
    private String subject;

    @NonNull
    @Column(name = "code",unique = true)
    @NotBlank(message = "Code can not be blank")
    private String code;

    @NonNull
    @Column(name = "description")
    @NotBlank(message = "Description can not be blank")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL)
    private List<Grade> grades;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
    )
    private Set<Student> students;

}
