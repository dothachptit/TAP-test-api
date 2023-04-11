package com.example.tap.repository;

import com.example.tap.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DivisionRepo extends JpaRepository<Division,Integer> {

    Optional <Division> findByName(String name);
    Optional <Division> findByAcronym(String acroym);
}
