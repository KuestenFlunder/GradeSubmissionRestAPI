package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}