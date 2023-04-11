package com.example.tap.repository;

import com.example.tap.entity.Examinee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamineeRepo extends JpaRepository<Examinee, Integer> {
}
