package com.ltp.gradesubmission.repository;

import com.ltp.gradesubmission.entity.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    Optional<List<Grade>> findByStudentId(Long studentId);

    Optional<List<Grade>> findByCourseId(Long courseId);

    void deleteByStudentIdAndCourseId(Long studentId,Long courseId);
}