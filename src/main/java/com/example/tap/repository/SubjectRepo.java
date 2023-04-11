package com.example.tap.repository;

import com.example.tap.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject,Integer> {
    Optional<Subject> findByName(String name);
}
