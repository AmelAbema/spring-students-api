package com.abema.springstudentsapi.Repository;

import com.abema.springstudentsapi.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
