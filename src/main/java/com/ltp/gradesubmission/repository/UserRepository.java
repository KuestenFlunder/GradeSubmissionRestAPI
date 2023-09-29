package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Course, Long> {

}