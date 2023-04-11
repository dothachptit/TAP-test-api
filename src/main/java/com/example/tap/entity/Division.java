package com.example.tap.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "division")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 55)
    private String name;

    @Column(name = "min_division_score")
    private Integer minDivisionScore;

    @Column(name = "acronym", length = 5)
    private String acronym;

    @OneToMany(mappedBy = "divisionid")
    List<DivisionSubject> subjects;
}