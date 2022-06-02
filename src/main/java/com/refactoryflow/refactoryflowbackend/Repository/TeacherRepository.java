package com.refactoryflow.refactoryflowbackend.Repository;

import com.refactoryflow.refactoryflowbackend.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>,
        TeacherRepositoryCustom{
}
