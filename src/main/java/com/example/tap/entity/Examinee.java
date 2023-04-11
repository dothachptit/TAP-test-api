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
@Table(name = "examinee")
public class Examinee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "acronym_of_division", length = 55)
    private String acronymOfDivision;

    @Column(name = "total_score")
    private Integer totalScore = 0;

    @Column(name = "division_score")
    private Integer divisionScore = 0;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "examineeid", fetch = FetchType.EAGER)
    private List<ExamineeSubject> examineeSubjects;
}