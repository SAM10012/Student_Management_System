package com.pauls.StudentManagementSystem.repository;

import com.pauls.StudentManagementSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
