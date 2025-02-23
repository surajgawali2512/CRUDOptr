package com.demo.repository;

import com.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    List<Teacher> findByName(String name);
    Optional<Teacher> findByEmail(String email);
}
