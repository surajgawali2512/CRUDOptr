package com.demo.repository;

import com.demo.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute,Long> {
}
