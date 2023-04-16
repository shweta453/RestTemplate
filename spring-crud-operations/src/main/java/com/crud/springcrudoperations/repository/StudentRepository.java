package com.crud.springcrudoperations.repository;

import com.crud.springcrudoperations.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByStuId(Integer stuId);
}
