package com.example.tap.repository;

import com.example.tap.entity.ConstantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstantEntityRepo extends JpaRepository<ConstantEntity, Integer> {
    Optional<ConstantEntity> findByName(String name);
}
