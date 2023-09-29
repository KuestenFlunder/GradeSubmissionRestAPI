package com.ltp.gradesubmission.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username can not be blank")
    @NotNull
    @Column(nullable = false,unique = true)
    private String username;

    @NonNull
    @NotBlank(message = "password can not be blank")
    @Column(nullable = false)
    private String password;


}
